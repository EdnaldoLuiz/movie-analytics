package com.ednaldoluiz.moviedash.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
import com.ednaldoluiz.moviedash.utils.TMDBUtil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Transactional
    public void getTmdbApiData() {
        String url = TMDBUtil.URL;
       
    }

    @Override
    @Cacheable(value = "movies", key = "#id", unless = "#result == null")
    public Page<Movie> findAllMovies(Pageable pageable) {
        log.info("Total de filmes: {}.", repository.count());
        Page<Movie> result = repository.findAll(pageable);
        return result;
    }
}
