CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    adult BOOLEAN,
    description TEXT,
    original_language VARCHAR(2),
    popularity DECIMAL(7, 3),
    poster VARCHAR(255),
    release_date DATE,
    title VARCHAR(255),
    video BOOLEAN,
    vote_average DECIMAL(5, 3),
    vote_count INT
);

CREATE TABLE genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);