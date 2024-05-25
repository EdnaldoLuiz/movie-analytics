package com.ednaldoluiz.moviedash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.projection.MovieProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

    void deleteAllByGenresIdIn(List<Long> genreIds);

    @Query("""
                SELECT m.title as title, m.voteAverage as voteAverage, m.releaseDate as releaseDate
                FROM Movie m
                INNER JOIN m.genres g
                WHERE g.id IN :genreIds
            """)
    Page<MovieProjection> findTop10ByGenreAndVoteAverage(Pageable pageable, List<Long> genreIds);

    @Query("""
                SELECT m
                FROM Movie m
                WHERE m.title LIKE %:keyword%
            """)
    Page<Movie> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("""
                SELECT m.title as title, m.voteAverage as voteAverage, m.releaseDate as releaseDate
                FROM Movie m
                INNER JOIN m.genres g
                WHERE g.id IN :genreIds AND YEAR(m.releaseDate) = :year
            """)
    Page<MovieProjection> findTop5ByGenreAndVoteAverageInYear(Pageable pageable, List<Long> genreIds, Integer year);

}