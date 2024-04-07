INSERT INTO surface_types (id, name, payment_per_minute) VALUES (1, 'Clay', 0.5);
INSERT INTO surface_types (id, name, payment_per_minute) VALUES (2, 'Hard', 0.7);

INSERT INTO courts (id, name, surface_type_id) VALUES (1, 'Clay court Secovce-Habes', 1);
INSERT INTO courts (id, name, surface_type_id) VALUES (2, 'Hard court Brno-Bosonohy', 2);
INSERT INTO courts (id, name, surface_type_id) VALUES (3, 'Hard court Brnoslava', 2);
INSERT INTO courts (id, name, surface_type_id) VALUES (4, 'Clay court Snina hlavni nadrazi', 1);

-- INSERT INTO users (phone_number, name, surname) VALUES ('123456789', 'John', 'Doe');
-- INSERT INTO users (phone_number, name, surname) VALUES ('987654321', 'Jane', 'Smith');

-- INSERT INTO reservations (id, user_id, court_id, game_type, start_time, end_time, created_time)
--     VALUES (1, '123456789', 1, 'SINGLES', '2024-03-25 10:00:00', '2024-03-25 11:00:00', '2024-03-21 10:00:00');
-- INSERT INTO reservations (id, user_id, court_id, game_type, start_time, end_time, created_time)
--     VALUES (2, '987654321', 2, 'SINGLES', '2024-03-26 12:00:00', '2024-03-26 14:00:00', '2024-03-18 10:00:00');
-- INSERT INTO reservations (id, user_id, court_id, game_type, start_time, end_time, created_time)
--     VALUES (3, '987654321', 2, 'DOUBLES', '2024-04-26 12:00:00', '2024-04-26 14:00:00', '2024-03-18 10:00:00');
