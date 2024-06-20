DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    adult BOOLEAN,
    description TEXT,
    original_language VARCHAR(2),
    popularity DECIMAL(7, 3),
    poster TEXT,
    release_date DATE,
    title VARCHAR(255),
    video BOOLEAN,
    vote_average DECIMAL(5, 3),
    vote_count INT
);

DROP TABLE IF EXISTS genres;

CREATE TABLE genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

DROP TABLE IF EXISTS movie_genres;

CREATE TABLE movie_genres (
    movie_id INT REFERENCES movies(id),
    genre_id INT REFERENCES genres(id),
    PRIMARY KEY (movie_id, genre_id)
);

INSERT INTO genres (id, name)
VALUES (28, 'Ação'),
    (12, 'Aventura'),
    (16, 'Animação'),
    (35, 'Comédia'),
    (80, 'Crime'),
    (99, 'Documentário'),
    (18, 'Drama'),
    (10751, 'Família'),
    (14, 'Fantasia'),
    (36, 'História'),
    (27, 'Terror'),
    (10402, 'Música'),
    (9648, 'Mistério'),
    (10749, 'Romance'),
    (878, 'Ficção Científica'),
    (10770, 'Filme de TV'),
    (53, 'Suspense'),
    (10752, 'Guerra'),
    (37, 'Ocidental');