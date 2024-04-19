package com.ednaldoluiz.moviedash.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ednaldoluiz.moviedash.constant.CacheConstants;
import com.ednaldoluiz.moviedash.dto.response.MovieResponseDTO;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
import com.ednaldoluiz.moviedash.repository.specification.MovieSpecification;
import com.ednaldoluiz.moviedash.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Override
    @Cacheable(cacheNames = CacheConstants.MOVIE_ALL, key = "#pageable.pageNumber", condition = "#result.size() > 10")
    public Page<MovieResponseDTO> findAllMovies(Pageable pageable) {
        log.info("Total de filmes: {}", repository.count());
        Page<Movie> result = repository.findAll(pageable);
        List<MovieResponseDTO> dtos = result.map(MovieResponseDTO::new).getContent();
        return new PageImpl<>(dtos, pageable, result.getTotalElements());
    }

    @Override
    @Cacheable(cacheNames = CacheConstants.MOVIE_TOP_10, key = "#genreIds", condition = "#result.size() > 5")
    public Page<MovieResponseDTO> findTop10Movies(Pageable pageable, List<Long> genreIds) {
        log.info("Gêneros: {}", genreIds);
        Page<MovieResponseDTO> result = repository.findTop10ByGenreAndVoteAverage(pageable, genreIds)
            .map(MovieResponseDTO::new);
        return new PageImpl<>(result.getContent(), pageable, result.getTotalElements());
    }

    @Override
    @Cacheable(cacheNames = CacheConstants.MOVIE_TOP_5_BY_YEAR, key = "{#genreIds, #year}", condition = "#result.size() == 5")
    public Page<MovieResponseDTO> findTop10MoviesByYear(Pageable pageable, List<Long> genreIds, Integer year) {
        log.info("Gêneros: {}, Ano: {}", genreIds, year);
        Page<MovieResponseDTO> result = repository.findTop5ByGenreAndVoteAverageInYear(pageable, genreIds, year)
            .map(MovieResponseDTO::new);
        return new PageImpl<>(result.getContent(), pageable, result.getTotalElements());
    }

    @Override
    //@Cacheable(key = "{#fieldName, #value, #greaterThan, #startDate, #endDate, #language}", unless = "#result == null")
    public Specification<Movie> queryBuilder(String fieldName, Double value, boolean greaterThan,
            LocalDate startDate, LocalDate endDate, String language) {
        return Specification
                .where(MovieSpecification.compareField(fieldName, value, greaterThan))
                .and(MovieSpecification.releasedBetween(startDate, endDate))
                .and(MovieSpecification.languageEquals(language));
    }

    @Override
    public Page<Movie> findMoviesByTitle(String keyword, Pageable pageable) {
        return repository.findByTitleContainingIgnoreCase(keyword, pageable);
    }
}
