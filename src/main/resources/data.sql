CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- =========================
-- COUNTRIES
-- =========================
INSERT INTO countries (id, title) VALUES
(gen_random_uuid(), 'США'),
(gen_random_uuid(), 'Россия'),
(gen_random_uuid(), 'Франция'),
(gen_random_uuid(), 'Япония'),
(gen_random_uuid(), 'Великобритания');

-- =========================
-- GENRES
-- =========================
INSERT INTO genres (id, title) VALUES
(gen_random_uuid(), 'Боевик'),
(gen_random_uuid(), 'Комедия'),
(gen_random_uuid(), 'Драма'),
(gen_random_uuid(), 'Фантастика'),
(gen_random_uuid(), 'Ужасы'),
(gen_random_uuid(), 'Мультфильм');

-- =========================
-- DIRECTORS
-- =========================
INSERT INTO directors (id, name) VALUES
(gen_random_uuid(), 'Кристофер Нолан'),
(gen_random_uuid(), 'Квентин Тарантино'),
(gen_random_uuid(), 'Фёдор Бондарчук'),
(gen_random_uuid(), 'Хаяо Миядзаки'),
(gen_random_uuid(), 'Дени Вильнёв');

-- =========================
-- FILM COMPANIES
-- =========================
INSERT INTO film_companies (id, title) VALUES
(gen_random_uuid(), 'Warner Bros'),
(gen_random_uuid(), 'Universal Pictures'),
(gen_random_uuid(), 'Мосфильм'),
(gen_random_uuid(), 'Pixar Animation Studios'),
(gen_random_uuid(), 'Legendary Pictures');

-- =========================
-- HALL TYPES
-- =========================
INSERT INTO hall_types (id, title, price_multiplier) VALUES
(gen_random_uuid(), 'Стандарт', 1.0),
(gen_random_uuid(), 'IMAX', 1.5),
(gen_random_uuid(), 'VIP', 2.0);

-- =========================
-- SEAT TYPES
-- =========================
INSERT INTO seat_types (id, title, price_multiplier) VALUES
(gen_random_uuid(), 'Обычное', 1.0),
(gen_random_uuid(), 'Комфорт', 1.3),
(gen_random_uuid(), 'VIP', 1.8);

-- =========================
-- USERS
-- =========================
--INSERT INTO users (id, full_name, email, phone_number, password, role) VALUES
--(gen_random_uuid(), 'Мария Петрова', 'user@example.com', '+79994445566', 'keklol', 'CLIENT'),
--(gen_random_uuid(), 'Администратор', 'admin@example.com', '+79990000000', 'lolkek', 'ADMIN');

-- =========================
-- HALLS
-- =========================
INSERT INTO halls (id, title, hall_type_id, rows, columns)
VALUES
(
    gen_random_uuid(),
    'Зал №1',
    (SELECT id FROM hall_types WHERE title = 'Стандарт'),
    5,
    5
),
(
    gen_random_uuid(),
    'VIP Зал',
    (SELECT id FROM hall_types WHERE title = 'VIP'),
    4,
    4
);

-- =========================
-- SEATS FOR HALL №1
-- =========================
INSERT INTO seats (id, hall_id, seat_type_id, seat_row, seat_column)
SELECT
    gen_random_uuid(),
    h.id,
    st.id,
    r,
    c
FROM halls h
CROSS JOIN generate_series(1, 5) AS r
CROSS JOIN generate_series(1, 5) AS c
JOIN seat_types st ON st.title = 'Обычное'
WHERE h.title = 'Зал №1';

-- =========================
-- SEATS FOR VIP HALL
-- =========================
INSERT INTO seats (id, hall_id, seat_type_id, seat_row, seat_column)
SELECT
    gen_random_uuid(),
    h.id,
    st.id,
    r,
    c
FROM halls h
CROSS JOIN generate_series(1, 4) AS r
CROSS JOIN generate_series(1, 4) AS c
JOIN seat_types st ON st.title = 'VIP'
WHERE h.title = 'VIP Зал';

-- =========================
-- MOVIES
-- =========================
INSERT INTO movies (
    id,
    title,
    duration,
    age_limit,
    poster_path,
    genre_id,
    country_id,
    director_id,
    film_company_id
)
VALUES
(
    gen_random_uuid(),
    'Начало',
    148,
    16,
    '/posters/inception.jpg',
    (SELECT id FROM genres WHERE title = 'Фантастика'),
    (SELECT id FROM countries WHERE title = 'США'),
    (SELECT id FROM directors WHERE name = 'Кристофер Нолан'),
    (SELECT id FROM film_companies WHERE title = 'Warner Bros')
),
(
    gen_random_uuid(),
    'Унесённые призраками',
    125,
    12,
    '/posters/spirited_away.jpg',
    (SELECT id FROM genres WHERE title = 'Мультфильм'),
    (SELECT id FROM countries WHERE title = 'Япония'),
    (SELECT id FROM directors WHERE name = 'Хаяо Миядзаки'),
    (SELECT id FROM film_companies WHERE title = 'Pixar Animation Studios')
),
(
    gen_random_uuid(),
    'Сталинград',
    135,
    16,
    '/posters/stalingrad.jpg',
    (SELECT id FROM genres WHERE title = 'Драма'),
    (SELECT id FROM countries WHERE title = 'Россия'),
    (SELECT id FROM directors WHERE name = 'Фёдор Бондарчук'),
    (SELECT id FROM film_companies WHERE title = 'Мосфильм')
);

-- =========================
-- SESSIONS
-- =========================
INSERT INTO sessions (
    id,
    movie_id,
    hall_id,
    date_time,
    base_price
)
VALUES
(
    gen_random_uuid(),
    (SELECT id FROM movies WHERE title = 'Начало'),
    (SELECT id FROM halls WHERE title = 'Зал №1'),
    '2026-05-15 18:00:00',
    450
),
(
    gen_random_uuid(),
    (SELECT id FROM movies WHERE title = 'Унесённые призраками'),
    (SELECT id FROM halls WHERE title = 'VIP Зал'),
    '2026-05-15 20:00:00',
    700
),
(
    gen_random_uuid(),
    (SELECT id FROM movies WHERE title = 'Сталинград'),
    (SELECT id FROM halls WHERE title = 'Зал №1'),
    '2026-05-16 19:30:00',
    500
);
