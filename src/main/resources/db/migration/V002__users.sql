---------- TABLES ----------

CREATE TABLE IF NOT EXISTS users
(
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    name         VARCHAR                                              NOT NULL,
    phone_number VARCHAR(16) CHECK (LENGTH(phone_number) = 16) UNIQUE NOT NULL,

    location_id  BIGINT REFERENCES locations (id) ON DELETE SET NULL ON UPDATE CASCADE
);

---------- COMMENTS ----------

COMMENT ON TABLE users IS 'Пользователи';

COMMENT ON COLUMN users.id IS 'ID';
COMMENT ON COLUMN users.name IS 'Имя';
COMMENT ON COLUMN users.phone_number IS 'Номер телефона';
COMMENT ON COLUMN users.location_id IS 'ID населенного пункта';

---------- FUNCTIONS ----------

CREATE OR REPLACE FUNCTION get_user_id(user_phone_number VARCHAR(16))
    RETURNS BIGINT
    LANGUAGE plpgsql
AS
$$
BEGIN
    RETURN (SELECT id FROM users WHERE phone_number = user_phone_number);
END;
$$;

---------- PROCEDURES ----------

CREATE OR REPLACE PROCEDURE create_user(
    IN new_user_name VARCHAR,
    IN new_user_phone_number VARCHAR(16),
    IN new_user_location_name VARCHAR,
    IN new_user_password_hash VARCHAR,
    IN new_user_role_names VARCHAR[],
    OUT new_user_id BIGINT
)
    LANGUAGE plpgsql
AS
$$
DECLARE
    new_user_location_id BIGINT;
    new_user_role_id     BIGINT;
    new_user_role_name   VARCHAR;

BEGIN
    new_user_location_id := get_location_id(new_user_location_name);
    IF new_user_location_id IS NULL THEN
        INSERT INTO locations (name)
        VALUES (new_user_location_name)
        RETURNING id INTO new_user_location_id;
        RAISE NOTICE 'Создан новый населенный пункт: % (ID: %)', new_user_location_name, new_user_location_id;
    END IF;

    INSERT INTO users (name, phone_number, location_id)
    VALUES (new_user_name, new_user_phone_number, new_user_location_id)
    RETURNING id INTO new_user_id;
    RAISE NOTICE 'Создан новый пользователь (ID: %)', new_user_id;

    INSERT INTO user_details (user_id, username, password_hash)
    VALUES (new_user_id, new_user_phone_number, new_user_password_hash);
    RAISE NOTICE 'Создана новая информация о пользователе';

    FOREACH new_user_role_name IN ARRAY new_user_role_names
        LOOP
            new_user_role_id := get_role_id(new_user_role_name);
            IF new_user_role_id IS NULL THEN
                RAISE EXCEPTION 'Роль с названием % не существует', new_user_role_name;
            END IF;

            INSERT INTO users_roles (user_id, role_id)
            VALUES (new_user_id, new_user_role_id);
            RAISE NOTICE 'Роль % (ID: %) добавлена для нового пользователя', new_user_role_name, new_user_role_id;
        END LOOP;
    RAISE NOTICE 'Все роли успешно добавлены для нового пользователя';

EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE 'При создании нового пользователя произошла ошибка: %', SQLERRM;
        ROLLBACK;
        RAISE;
END;
$$;

---------- INSERTS ----------

INSERT INTO users (name, phone_number, location_id)
VALUES ('Михаил', '+7-996-380-03-61', get_location_id('Новосибирск'));
