package com.ednaldoluiz.moviedash.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ednaldoluiz.moviedash.dto.response.MovieResponseDTO;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.projection.MovieProjection;

public interface MovieService {
    
    Page<MovieResponseDTO> findAllMovies(Pageable pageable);

    Page<MovieProjection> findTop10Movies(Pageable pageable, List<Long> genreIds);

    Page<Movie> findMoviesByTitle(String keyword, Pageable pageable);

    Specification<Movie> queryBuilder(
        String fieldName, Double value, boolean greaterThan, LocalDate startDate, LocalDate endDate, String language);
}
