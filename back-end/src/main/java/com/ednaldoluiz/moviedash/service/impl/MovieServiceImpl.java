package com.ednaldoluiz.moviedash.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ednaldoluiz.moviedash.constant.CacheConstants;
import com.ednaldoluiz.moviedash.dto.response.MovieResponseDTO;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
import com.ednaldoluiz.moviedash.repository.projection.MovieProjection;
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
        cacheNames = CacheConstants.MOVIE_CACHE, 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieResponseDTO> findAllMovies(Pageable pageable) {
        log.info("Total de filmes: {}", repository.count());
        Page<Movie> result = repository.findAll(pageable);
        List<MovieResponseDTO> dtos = result.map(MovieResponseDTO::new).getContent();
        return new PageImpl<>(dtos, pageable, result.getTotalElements());
    }

    @Override
    @Cacheable(
        cacheNames = CacheConstants.MOVIE_CACHE, 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort,#genreIds}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieResponseDTO> findTop10Movies(Pageable pageable, List<Long> genreIds) {
        log.info("Gêneros: {}", genreIds);
        Page<MovieProjection> result = repository.findTop10ByGenreAndVoteAverage(pageable, genreIds);
        List<MovieResponseDTO> dtos = result.map(MovieResponseDTO::new).getContent();
        return new PageImpl<>(dtos, pageable, result.getTotalElements());
    }

    @Override
    @Cacheable(
        cacheNames = CacheConstants.MOVIE_CACHE, 
        key = "{#genreIds, #year}", 
        unless = "#result.numberOfElements == 5")
    public Page<MovieResponseDTO> findTop5MoviesByYear(Pageable pageable, List<Long> genreIds, Integer year) {
        log.info("Gêneros: {}, Ano: {}", genreIds, year);
        Page<MovieResponseDTO> result = repository.findTop5ByGenreAndVoteAverageInYear(pageable, genreIds, year)
            .map(MovieResponseDTO::new);
        return new PageImpl<>(result.getContent(), pageable, result.getTotalElements());
    }

    @Override
    public Page<Movie> findMoviesByTitle(String keyword, Pageable pageable) {
        return repository.findByTitleContainingIgnoreCase(keyword, pageable);
    }
}
