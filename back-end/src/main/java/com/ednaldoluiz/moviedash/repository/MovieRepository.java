package com.ednaldoluiz.moviedash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.projection.movie.MovieProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m JOIN FETCH m.genres")
    List<Movie> findAllWithGenres();

    Optional<Movie> findByTitle(String title);

    void deleteAllByGenresIdIn(List<Long> genreIds);

    @Query("""
            SELECT new com.ednaldoluiz.moviedash.repository.projection.movie.MovieProjection(
                m.title, m.voteAverage, m.releaseDate, m.poster
            )
            FROM Movie m
            INNER JOIN m.genres g
            WHERE g.id IN :genreIds
                """)
    Page<MovieProjection> findTop10ByGenreAndVoteAverage(Pageable pageable, List<Long> genreIds);

    @Query("""
                SELECT new com.ednaldoluiz.moviedash.repository.projection.movie.MovieProjection(
                    m.title, m.voteAverage, m.releaseDate, m.poster
                )
                FROM Movie m
                WHERE m.title LIKE %:keyword%
            """)
    Page<MovieProjection> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("""
                SELECT new com.ednaldoluiz.moviedash.repository.projection.movie.MovieProjection(
                    m.title, m.voteAverage, m.releaseDate, m.poster
                )
                FROM Movie m
                INNER JOIN m.genres g
                WHERE g.id IN :genreIds AND YEAR(m.releaseDate) = :year
            """)
    Page<MovieProjection> findTop5ByGenreAndVoteAverageInYear(Pageable pageable, List<Long> genreIds, Integer year);

    @Query("""
            SELECT YEAR(m.releaseDate) as year, COUNT(m.id) as movieCount
            FROM Movie m
            WHERE (:year IS NULL OR YEAR(m.releaseDate) = :year)
            GROUP BY YEAR(m.releaseDate)
            """)
    List<Object[]> findMoviesCountByYear(Integer year);

}