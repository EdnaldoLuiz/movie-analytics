package com.ednaldoluiz.moviedash.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.service.MovieService;
import static com.ednaldoluiz.moviedash.utils.ResponseUtil.*;

import static com.ednaldoluiz.moviedash.constant.APIConstants.API_V1;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(API_V1 + "movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @GetMapping
    public ResponseEntity<Page<Movie>> allMovies(
        @RequestParam(defaultValue = PAGE_NUMBER) int page, 
        @RequestParam(defaultValue = PAGE_SIZE) int size) {
            
        Pageable pageable = PageRequest.of(page, size, Sort.by("ano").descending());
        return ResponseEntity.ok(service.findAllMovies(pageable));
    }
    
}
