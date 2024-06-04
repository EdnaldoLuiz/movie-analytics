package com.ednaldoluiz.moviedash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ednaldoluiz.moviedash.model.Genre;
import com.ednaldoluiz.moviedash.repository.projection.GenreProjection;
import com.ednaldoluiz.moviedash.repository.projection.genre.PopularMoviesByGenreProjection;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g.name as name, COUNT(m) as quantity FROM Genre g INNER JOIN g.movies m GROUP BY g.name ORDER BY quantity DESC")
    List<GenreProjection> findMostPopularGenres();

    @Query("""
            SELECT g.name as name, COUNT(m) as quantity,
            (SELECT COUNT(m2) FROM Movie m2) as total
            FROM Genre g
            INNER JOIN g.movies m
            WHERE g.id = :genreId
            GROUP BY g.name
            """)
    GenreProjection countByGenresId(Long genreId);

    @Query("""
            SELECT new com.ednaldoluiz.moviedash.repository.projection.genre.PopularMoviesByGenreProjection(
                g.name, g.id, m.title, m.popularity
            )
            FROM Genre g
            INNER JOIN g.movies m
            WHERE (:genre = 0 OR g.id = :genre)
            ORDER BY m.popularity DESC
            """)
    List<PopularMoviesByGenreProjection> findMostPopularMoviesByGenre(Long genre);

    @Query("""
            SELECT g.name as name, COUNT(m) as quantity
            FROM Genre g
            INNER JOIN g.movies m
            GROUP BY g.name
            HAVING COUNT(m) > 1
                """)
    List<Object[]> findGenresWithMoreThanOneMovie();

    @Query("""
            SELECT g.name as genre, ROUND(AVG(m.voteAverage), 2) as averageVotes
            FROM Genre g
            INNER JOIN g.movies m
            GROUP BY g.name
            ORDER BY averageVotes DESC
            """)
    List<Object[]> findGenresWithHighestAverageVotes();

}
