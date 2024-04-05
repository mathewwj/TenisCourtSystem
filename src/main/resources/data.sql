INSERT INTO surface_types (id, name, payment_per_minute) VALUES (1, 'Clay', 0.5);
INSERT INTO surface_types (id, name, payment_per_minute) VALUES (2, 'Hard', 0.7);
INSERT INTO surface_types (id, name, payment_per_minute) VALUES (3, 'Grass', 0.9);

INSERT INTO users (phone_number, name, surname) VALUES ('123456789', 'John', 'Doe');
INSERT INTO users (phone_number, name, surname) VALUES ('987654321', 'Jane', 'Smith');

INSERT INTO courts (id, name, surface_type_id) VALUES (1, 'Clay court', 1);
INSERT INTO courts (id, name, surface_type_id) VALUES (2, 'Hard court', 2);

INSERT INTO reservations (id, user_id, court_id, is_single, start_time, end_time) VALUES (1, '123456789', 1, true, '2024-03-25 10:00:00', '2024-03-25 11:00:00');
INSERT INTO reservations (id, user_id, court_id, is_single, start_time, end_time) VALUES (2, '987654321', 2, false, '2024-03-26 12:00:00', '2024-03-26 14:00:00');
