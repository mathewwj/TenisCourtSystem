DROP TABLE IF EXISTS surface_types;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS courts;
DROP TABLE IF EXISTS reservations;

CREATE TABLE IF NOT EXISTS surface_types (
     id LONG PRIMARY KEY,
     name VARCHAR2 NOT NULL,
     payment_per_minute DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
     phone_number VARCHAR2 PRIMARY KEY,
     name VARCHAR2 NOT NULL,
     surname VARCHAR2 NOT NULL
);

CREATE TABLE IF NOT EXISTS courts (
    id LONG PRIMARY KEY,
    surface_type_id LONG ,
    name VARCHAR2 NOT NULL,
    FOREIGN KEY (surface_type_id) REFERENCES surface_types(id)
);

CREATE TABLE IF NOT EXISTS reservations (
    id LONG PRIMARY KEY,
    user_id VARCHAR2 NOT NULL,
    court_id LONG NOT NULL,
    is_single BOOLEAN NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    created_time TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(phone_number),
    FOREIGN KEY (court_id) REFERENCES courts(id)
);
