package com.ednaldoluiz.moviedash.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.ednaldoluiz.moviedash.constant.CacheConstants.*;
import com.ednaldoluiz.moviedash.dto.response.MovieResponseDTO;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
import com.ednaldoluiz.moviedash.repository.projection.movie.MoviesCountByYearProjection;
import com.ednaldoluiz.moviedash.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@CacheConfig(cacheNames = MOVIE_CACHE)
@RequiredArgsConstructor
public class MovieService extends AbstractService {

    private final MovieRepository repository;

    @Cacheable(
        cacheNames = "all", 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieResponseDTO> findAllMovies(Pageable pageable) {
        log.info("Total de filmes: {}", repository.count());
        return convertPage(repository.findAll(pageable), MovieResponseDTO::new);
    }

    @Cacheable(
        cacheNames = "top10", 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort,#genreIds}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieResponseDTO> findTop10Movies(Pageable pageable, List<Long> genreIds) {
        log.info("Gêneros: {}", genreIds);
        return convertPage(repository.findTop10ByGenreAndVoteAverage(pageable, genreIds), MovieResponseDTO::new);
    }

    @Cacheable(
        cacheNames = "top5", 
        key = "{#genreIds, #year}", 
        unless = "#result.numberOfElements == 5")
    public Page<MovieResponseDTO> findTop5MoviesByYear(Pageable pageable, List<Long> genreIds, Integer year) {
        log.info("Gêneros: {}, Ano: {}", genreIds, year);
        return convertPage(repository.findTop5ByGenreAndVoteAverageInYear(pageable, genreIds, year), MovieResponseDTO::new);
    }

    @Cacheable(
        cacheNames = "year", 
        key = "{#year}", 
        unless = "#result.size() < 5")
    public List<MoviesCountByYearProjection> findMoviesCountByYear(Integer year) {
        log.info("Contando filmes por ano");
        List<MoviesCountByYearProjection> moviesCountByYear = repository.findMoviesCountByYear(year);
        moviesCountByYear.sort(Comparator.comparing(MoviesCountByYearProjection::year));
        return moviesCountByYear;
    }

    public Page<Movie> findMoviesByTitle(String keyword, Pageable pageable) {
        log.info("Buscando por: {}", keyword);
        return repository.findByTitleContainingIgnoreCase(keyword, pageable);
    }
}
