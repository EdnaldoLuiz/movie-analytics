package com.ednaldoluiz.moviedash.controller;

import static com.ednaldoluiz.moviedash.constant.APIConstants.*;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ednaldoluiz.moviedash.docs.GenreDocs.*;
import com.ednaldoluiz.moviedash.repository.projection.GenreProjection;
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
@RequestMapping(API_V1 + GENRES)
@RequiredArgsConstructor
@Tag(name = GENRE_CONTROLLER_NAME, description = GENRE_CONTROLLER_DESCRIPTION)
public class GenreController {

    private final GenreService service;

    @GetMapping("/count")
    @Operation(summary = GENRE_COUNT_SUMMARY, description = GENRE_COUNT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GENRE_COUNT_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = GENRE_COUNT_RESPONSE_404),
    })
    public ResponseEntity<GenreProjection> countGenres(
            @Parameter(description = "ID do Gênero") @RequestParam Long genreId) {

        log.info("Contando filmes por gênero: {}", genreId);
        return ResponseEntity.ok(service.countByGenresId(genreId));
    }

    @GetMapping("/total")
    @Operation(summary = GENRE_TOTAL_SUMMARY, description = GENRE_TOTAL_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GENRE_TOTAL_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = GENRE_TOTAL_RESPONSE_404),
    })
    public ResponseEntity<Map<String, Long>> countTotalGenres() {

        log.info("Contando total de gêneros");
        return ResponseEntity.ok(service.countTotalGenres());
    }

    @GetMapping("/popular-genres")
    @Operation(summary = GENRE_COUNT_SUMMARY, description = GENRE_COUNT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GENRE_COUNT_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = GENRE_COUNT_RESPONSE_404),
    })
    public ResponseEntity<List<GenreProjection>> mostPopularGenres(
            @Parameter(description = "ID do Gênero") @RequestParam Long genreId) {

        log.info("Contando filmes por gênero: {}", genreId);
        return ResponseEntity.ok(service.getMostPopularGenres());
    }

    @GetMapping("/popularity-growth")
    @Operation(summary = GENRE_POPULARITY_SUMMARY, description = GENRE_POPULARITY_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GENRE_POPULARITY_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = GENRE_POPULARITY_RESPONSE_404),
    })
    public ResponseEntity<Map<String, Long>> countGenresHighestPopularity(
            @Parameter(description = "Ano atual") @RequestParam Integer currentYear,
            @Parameter(description = "Ano anterior") @RequestParam Integer previousYear) {

        log.info("Contando gêneros com maior crescimento de popularidade: {} e {}", currentYear, previousYear);
        return ResponseEntity.ok(service.countGenresHighestPopularityGrowth(currentYear, previousYear));
    }
}
