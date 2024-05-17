package com.ednaldoluiz.moviedash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ednaldoluiz.moviedash.model.Genre;
import com.ednaldoluiz.moviedash.repository.projection.GenreProjection;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

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
            SELECT g.name as name, COUNT(m) as quantity
            FROM Genre g
            INNER JOIN g.movies m
            GROUP BY g.name
            HAVING COUNT(m) > 1
                """)
    List<Object[]> findGenresWithMoreThanOneMovie();

    @Query("""
                SELECT g.name as name, AVG(m.popularity) as popularity,
                (SELECT AVG(m2.popularity) FROM Movie m2 INNER JOIN m2.genres g2 WHERE g2.id = g.id AND YEAR(m2.releaseDate) = :previousYear) as previousAvgPopularity
                FROM Genre g
                INNER JOIN g.movies m
                WHERE YEAR(m.releaseDate) = :currentYear
                GROUP BY g.name
                HAVING AVG(m.popularity) > (SELECT AVG(m2.popularity) FROM Movie m2 INNER JOIN m2.genres g2 WHERE g2.id = g.id AND YEAR(m2.releaseDate) = :previousYear)
            """)
    List<Object[]> findGenresWithHighestGrowthInPopularity(Integer currentYear, Integer previousYear);

    @Query("SELECT g.name, COUNT(m) as quantity FROM Genre g INNER JOIN g.movies m GROUP BY g.name ORDER BY quantity DESC")
    List<GenreProjection> findMostPopularGenres();

}
