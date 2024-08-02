---------- TABLES ----------

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    role_id BIGINT REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE,

    PRIMARY KEY (user_id, role_id)
);

---------- COMMENTS ----------

COMMENT ON TABLE users_roles IS 'Роли пользователей';

COMMENT ON COLUMN users_roles.user_id IS 'ID пользователя';
COMMENT ON COLUMN users_roles.role_id IS 'ID роли';

---------- INSERTS ----------

INSERT INTO users_roles (user_id, role_id)
VALUES (get_user_id('+7-996-380-03-61'), get_role_id('ROLE_OWNER')),
       (get_user_id('+7-996-380-03-61'), get_role_id('ROLE_ADMIN')),
       (get_user_id('+7-996-380-03-61'), get_role_id('ROLE_USER'));
