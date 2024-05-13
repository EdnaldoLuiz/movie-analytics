package com.ednaldoluiz.moviedash.service.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.ednaldoluiz.moviedash.constant.CacheConstants.*;
import com.ednaldoluiz.moviedash.dto.response.MovieResponseDTO;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
import com.ednaldoluiz.moviedash.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Override
    @Cacheable(
        cacheNames = MOVIE_CACHE, 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieResponseDTO> findAllMovies(Pageable pageable) {
        log.info("Total de filmes: {}", repository.count());
        return convertPage(repository.findAll(pageable), MovieResponseDTO::new);
    }

    @Override
    @Cacheable(
        cacheNames = MOVIE_CACHE, 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort,#genreIds}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieResponseDTO> findTop10Movies(Pageable pageable, List<Long> genreIds) {
        log.info("Gêneros: {}", genreIds);
        return convertPage(repository.findTop10ByGenreAndVoteAverage(pageable, genreIds), MovieResponseDTO::new);
    }

    @Override
    @Cacheable(
        cacheNames = MOVIE_CACHE, 
        key = "{#genreIds, #year}", 
        unless = "#result.numberOfElements == 5")
    public Page<MovieResponseDTO> findTop5MoviesByYear(Pageable pageable, List<Long> genreIds, Integer year) {
        log.info("Gêneros: {}, Ano: {}", genreIds, year);
        return convertPage(repository.findTop5ByGenreAndVoteAverageInYear(pageable, genreIds, year), MovieResponseDTO::new);
    }

    @Override
    public Page<Movie> findMoviesByTitle(String keyword, Pageable pageable) {
        log.info("Buscando por: {}", keyword);
        return repository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    private <T, M> Page<M> convertPage(Page<T> page, Function<T, M> converter) {
        List<M> content = page.getContent()
            .stream()
            .map(converter)
            .toList();
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
