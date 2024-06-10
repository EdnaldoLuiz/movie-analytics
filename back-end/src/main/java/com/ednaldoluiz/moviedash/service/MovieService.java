package com.ednaldoluiz.moviedash.service;

import static com.ednaldoluiz.moviedash.constant.CacheConstants.MOVIE_CACHE;
import static com.ednaldoluiz.moviedash.utils.APIUtils.sortByValue;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ednaldoluiz.moviedash.repository.MovieRepository;
import com.ednaldoluiz.moviedash.repository.projection.movie.MovieProjection;
import com.ednaldoluiz.moviedash.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService extends AbstractService {

    private final MovieRepository repository;

    @Cacheable(
        cacheNames = MOVIE_CACHE + "all", 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieProjection> findAllMovies(Pageable pageable) {
        log.info("Total de filmes: {}", repository.count());
        return repository.findAll(pageable).map(MovieProjection::new);
    }

    @Cacheable(
        cacheNames = MOVIE_CACHE + "top10", 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort,#genreIds}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieProjection> findTop10Movies(Pageable pageable, List<Long> genreIds) {
        log.info("Gêneros: {}", genreIds);
        return repository.findTop10ByGenreAndVoteAverage(pageable, genreIds);
    }

    @Cacheable(
        cacheNames = MOVIE_CACHE + "top5", 
        key = "{#genreIds, #year}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieProjection> findTop5MoviesByYear(Pageable pageable, List<Long> genreIds, Integer year) {
        log.info("Gêneros: {}, Ano: {}", genreIds, year);
        return repository.findTop5ByGenreAndVoteAverageInYear(pageable, genreIds, year);
    }

    @Cacheable(
        cacheNames = MOVIE_CACHE + "year", 
        key = "{#year}", 
        unless = "#result.size() < 5")
    public Map<String, Long> findMoviesCountByYear(Integer year) {
        log.info("Contando filmes por ano");
        Map<String, Long> movies = convertToMap(repository.findMoviesCountByYear(year), Long.class);
        return sortByValue(movies);
    }

    public Page<MovieProjection> findMoviesByTitle(String keyword, Pageable pageable) {
        log.info("Buscando por: {}", keyword);
        return repository.findByTitleContainingIgnoreCase(keyword, pageable);
    }
}
