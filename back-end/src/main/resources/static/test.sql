-- Active: 1711201219368@@127.0.0.1@5434@postgres
SELECT m1_0.id, m1_0.adult, m1_0.description, m1_0.original_language, m1_0.popularity, m1_0.poster, m1_0.release_date, m1_0.title, m1_0.video, m1_0.vote_average, m1_0.vote_count
FROM movies m1_0
ORDER BY m1_0.release_date DESC
OFFSET 10 ROWS
FETCH FIRST 20 ROWS ONLY;

-- Hibernate:
SELECT count(m1_0.id)
FROM movies m1_0;