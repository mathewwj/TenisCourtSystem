DROP TABLE IF EXISTS surface_types;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS courts;
DROP TABLE IF EXISTS reservations;

CREATE TABLE IF NOT EXISTS surface_types (
     id BIGINT PRIMARY KEY,
     name VARCHAR2 NOT NULL,
     payment_per_minute DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
     phone_number VARCHAR2 PRIMARY KEY,
     name VARCHAR2 NOT NULL,
     surname VARCHAR2 NOT NULL
);

CREATE TABLE IF NOT EXISTS courts (
    id BIGINT PRIMARY KEY,
    surface_type_id BIGINT,
    name VARCHAR2 NOT NULL,
    FOREIGN KEY (surface_type_id) REFERENCES surface_types(id)
);

CREATE TABLE IF NOT EXISTS reservations (
    id BIGINT PRIMARY KEY,
    user_id VARCHAR2,
    court_id BIGINT,
    is_single BOOLEAN,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(phone_number),
    FOREIGN KEY (court_id) REFERENCES courts(id)
);
