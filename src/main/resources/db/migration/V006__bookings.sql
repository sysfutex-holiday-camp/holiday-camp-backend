---------- TABLES ----------

CREATE TABLE IF NOT EXISTS bookings
(
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    user_id          BIGINT REFERENCES users (id) ON DELETE SET NULL ON UPDATE CASCADE,

    number_of_people INTEGER CHECK (number_of_people > 0) NOT NULL,

    created_at       TIMESTAMP DEFAULT NOW()              NOT NULL,

    arrival          TIMESTAMP                            NOT NULL,
    is_arrived       BOOLEAN   DEFAULT FALSE              NOT NULL,
    arrived_at       TIMESTAMP DEFAULT NULL,

    departure        TIMESTAMP                            NOT NULL,
    is_departed      BOOLEAN   DEFAULT FALSE              NOT NULL,
    departed_at      TIMESTAMP DEFAULT NULL,

    is_confirmed     BOOLEAN   DEFAULT FALSE              NOT NULL,
    confirmed_at     TIMESTAMP DEFAULT NULL,

    is_canceled      BOOLEAN   DEFAULT FALSE              NOT NULL,
    canceled_at      TIMESTAMP DEFAULT NULL,

    CONSTRAINT booking_timestamps CHECK (arrival <= departure)
);

---------- COMMENTS ----------

COMMENT ON TABLE bookings IS 'Бронирования';

COMMENT ON COLUMN bookings.id IS 'ID';
COMMENT ON COLUMN bookings.user_id IS 'ID пользователя';
COMMENT ON COLUMN bookings.number_of_people IS 'Количество людей';
COMMENT ON COLUMN bookings.created_at IS 'Дата и время создания бронирования';
COMMENT ON COLUMN bookings.arrival IS 'Дата и время прибытия туриста';
COMMENT ON COLUMN bookings.is_arrived IS 'Прибыл ли турист';
COMMENT ON COLUMN bookings.arrived_at IS 'Дата и время фактического прибытия туриста';
COMMENT ON COLUMN bookings.departure IS 'Дата и время отъезда туриста';
COMMENT ON COLUMN bookings.is_departed IS 'Уехал ли турист';
COMMENT ON COLUMN bookings.departed_at IS 'Дата и время фактического отъезда туриста';
COMMENT ON COLUMN bookings.is_confirmed IS 'Подтверждено ли бронирование';
COMMENT ON COLUMN bookings.confirmed_at IS 'Дата и время подтверждения бронирования';
COMMENT ON COLUMN bookings.is_canceled IS 'Отменено ли бронирование';
COMMENT ON COLUMN bookings.canceled_at IS 'Дата и время отмены бронирования';

---------- TRIGGERS ----------

CREATE OR REPLACE FUNCTION update_arrived_at_function()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF OLD.is_arrived = FALSE AND NEW.is_arrived = TRUE THEN
        NEW.arrived_at = NOW();
    END IF;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER update_arrived_at_trigger
    BEFORE UPDATE
    ON bookings
    FOR EACH ROW
EXECUTE FUNCTION update_arrived_at_function();

CREATE OR REPLACE FUNCTION update_is_arrived_function()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF OLD.arrived_at IS NULL AND NEW.arrived_at IS NOT NULL THEN
        NEW.is_arrived = TRUE;
    END IF;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER update_is_arrived_trigger
    BEFORE UPDATE
    ON bookings
    FOR EACH ROW
EXECUTE FUNCTION update_is_arrived_function();

CREATE OR REPLACE FUNCTION update_departed_at_function()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF OLD.is_departed = FALSE AND NEW.is_departed = TRUE THEN
        NEW.departed_at = NOW();
    END IF;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER update_departed_at_trigger
    BEFORE UPDATE
    ON bookings
    FOR EACH ROW
EXECUTE FUNCTION update_departed_at_function();

CREATE OR REPLACE FUNCTION update_is_departed_function()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF OLD.departed_at IS NULL AND NEW.departed_at IS NOT NULL THEN
        NEW.is_departed = TRUE;
    END IF;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER update_is_departed_trigger
    BEFORE UPDATE
    ON bookings
    FOR EACH ROW
EXECUTE FUNCTION update_is_departed_function();

CREATE OR REPLACE FUNCTION update_confirmed_at_function()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF OLD.is_confirmed = FALSE AND NEW.is_confirmed = TRUE THEN
        NEW.confirmed_at = NOW();
    END IF;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER update_confirmed_at_trigger
    BEFORE UPDATE
    ON bookings
    FOR EACH ROW
EXECUTE FUNCTION update_confirmed_at_function();

CREATE OR REPLACE FUNCTION update_is_confirmed_function()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF OLD.confirmed_at IS NULL AND NEW.confirmed_at IS NOT NULL THEN
        NEW.is_confirmed = TRUE;
    END IF;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER update_is_confirmed_trigger
    BEFORE UPDATE
    ON bookings
    FOR EACH ROW
EXECUTE FUNCTION update_is_confirmed_function();

CREATE OR REPLACE FUNCTION update_canceled_at_function()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF OLD.is_canceled = FALSE AND NEW.is_canceled = TRUE THEN
        NEW.canceled_at = NOW();
    END IF;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER update_canceled_at_trigger
    BEFORE UPDATE
    ON bookings
    FOR EACH ROW
EXECUTE FUNCTION update_canceled_at_function();

CREATE OR REPLACE FUNCTION update_is_canceled_function()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF OLD.canceled_at IS NULL AND NEW.canceled_at IS NOT NULL THEN
        NEW.is_canceled = TRUE;
    END IF;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER update_is_canceled_trigger
    BEFORE UPDATE
    ON bookings
    FOR EACH ROW
EXECUTE FUNCTION update_is_canceled_function();
