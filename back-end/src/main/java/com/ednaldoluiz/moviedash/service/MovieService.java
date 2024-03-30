package com.ednaldoluiz.moviedash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ednaldoluiz.moviedash.model.Movie;

public interface MovieService {
    
    Page<Movie> findAllMovies(Pageable pageable);
}
