package com.ednaldoluiz.moviedash.controller;

import static com.ednaldoluiz.moviedash.constant.APIConstants.*;
import static com.ednaldoluiz.moviedash.docs.GenreDocs.*;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldoluiz.moviedash.model.enums.GenreType;
import com.ednaldoluiz.moviedash.repository.projection.genre.GenreProjection;
import com.ednaldoluiz.moviedash.repository.projection.genre.PopularMoviesByGenreProjection;
import com.ednaldoluiz.moviedash.service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(API_V1 + Genre.GENRES)
@RequiredArgsConstructor
@Tag(name = GENRE_CONTROLLER_NAME, description = GENRE_CONTROLLER_DESCRIPTION)
public class GenreController {

    private final GenreService service;

    @GetMapping(Genre.COUNT)
    @Operation(summary = GENRE_COUNT_SUMMARY, description = GENRE_COUNT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GENRE_COUNT_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = GENRE_COUNT_RESPONSE_404),
    })
    public ResponseEntity<GenreProjection> countGenres(
            @Parameter(description = "Gênero") @RequestParam GenreType genre) {

        log.info("Contando filmes por gênero: {}", genre);
        return ResponseEntity.ok(service.countByGenresId(genre.getValue()));
    }

    @GetMapping(Genre.TOTAL)
    @Operation(summary = GENRE_TOTAL_SUMMARY, description = GENRE_TOTAL_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GENRE_TOTAL_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = GENRE_TOTAL_RESPONSE_404),
    })
    public ResponseEntity<Map<String, Long>> countTotalGenres() {

        log.info("Contando total de gêneros");
        return ResponseEntity.ok(service.countTotalGenres());
    }

    @GetMapping(Genre.VOTE_AVERAGE)
    @Operation(summary = GENRE_VOTE_AVERAGE_SUMMARY, description = GENRE_VOTE_AVERAGE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GENRE_VOTE_AVERAGE_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = GENRE_VOTE_AVERAGE_RESPONSE_404),
    })
    public ResponseEntity<Map<String, Double>> countVoteAverage() {
    
        log.info("Calculando a média de votos por gênero");
        return ResponseEntity.ok(service.countGenresWithHighestAverageVotes());
    }

    @GetMapping(Genre.POPULAR_GENRES)
    @Operation(summary = GENRE_POPULARITY_SUMMARY, description = GENRE_POPULARITY_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GENRE_POPULARITY_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = GENRE_POPULARITY_RESPONSE_404),
    })
    public ResponseEntity<List<GenreProjection>> mostPopularGenres() {
        log.info("Contando filmes por gênero");
        return ResponseEntity.ok(service.getMostPopularGenres());
    }

    @GetMapping(Genre.POPULAR_MOVIES)
    @Operation(summary = GENRE_POPULAR_MOVIES_SUMMARY, description = GENRE_POPULAR_MOVIES_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GENRE_POPULAR_MOVIES_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = GENRE_POPULAR_MOVIES_RESPONSE_404),
    })
    public ResponseEntity<List<PopularMoviesByGenreProjection>> mostPopularMovies(
            @Parameter(description = "Gênero") @RequestParam GenreType genre) {

        log.info("Buscando filmes mais populares por gênero: {}", genre);
        return ResponseEntity.ok(service.getMostPopularMoviesByGenre(genre.getValue()));
    }
}
