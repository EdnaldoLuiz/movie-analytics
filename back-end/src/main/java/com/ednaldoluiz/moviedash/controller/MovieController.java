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

import com.ednaldoluiz.moviedash.dto.response.MovieResponseDTO;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.model.enums.MovieSortType;
import com.ednaldoluiz.moviedash.repository.projection.movie.MoviesCountByYearProjection;
import com.ednaldoluiz.moviedash.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.ednaldoluiz.moviedash.utils.APIUtils.*;
import static com.ednaldoluiz.moviedash.constant.APIConstants.*;

import static com.ednaldoluiz.moviedash.docs.MovieDocs.*;

@Slf4j
@RestController
@RequestMapping(API_V1 + MOVIES)
@RequiredArgsConstructor
@Tag(name = MOVIE_CONTROLLER_NAME, description = MOVIE_CONTROLLER_DESCRIPTION)
public class MovieController {

    private final MovieService service;

    @GetMapping(ALL)
    @Operation(summary = MOVIE_ALL_SUMMARY, description = MOVIE_ALL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MOVIE_ALL_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = MOVIE_ALL_RESPONSE_404),
    })
    public ResponseEntity<Page<MovieResponseDTO>> allMovies(
            @Parameter(description = "Número da Página") @Min(1) @RequestParam(defaultValue = PAGE_NUMBER) int page,
            @Parameter(description = "Tamanho da Página") @Min(1) @RequestParam(defaultValue = PAGE_SIZE) int size,
            @Parameter(description = "Campo de Ordenação") @RequestParam(defaultValue = SORT_DEFAULT) MovieSortType sort) {

        log.info("Listando todos os filmes na página {} com tamanho de {} ordenado por {}", page, size, sort);
        Pageable pageable = PageRequest.of(page - 1, size, getSort(sort, "ASC"));
        return ResponseEntity.ok(service.findAllMovies(pageable));
    }

    @GetMapping("/top10")
    @Operation(summary = MOVIE_TOP10_SUMMARY, description = MOVIE_TOP_10)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MOVIE_TOP10_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = MOVIE_TOP10_RESPONSE_404),
    })
    public ResponseEntity<Page<MovieResponseDTO>> top10Movies(
            @Parameter(description = "IDs dos Gêneros") @RequestParam(defaultValue = "0") List<Long> genreIds) {

        log.info("Listando os 10 melhores filmes por gênero: {}", genreIds);
        Pageable pageable = PageRequest.of(0, 10, Sort.by(SORT_DEFAULT).descending());
        return ResponseEntity.ok(service.findTop10Movies(pageable, genreIds));
    }

    @GetMapping("/top5")
    @Operation(summary = MOVIE_TOP5_SUMMARY, description = MOVIE_TOP_5)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MOVIE_TOP5_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = MOVIE_TOP5_RESPONSE_404),
    })
    public ResponseEntity<Page<MovieResponseDTO>> top5MoviesPerYear(
            @Parameter(description = "IDs dos Gêneros") @RequestParam(defaultValue = "0") List<Long> genreIds,
            @Parameter(description = "Ano de Lançamento") @RequestParam Integer year) {

        log.info("Listando os 5 melhores filmes por gênero no ano de {}: {}", year, genreIds);
        Pageable pageable = PageRequest.of(0, 5, Sort.by(SORT_DEFAULT).descending());
        return ResponseEntity.ok(service.findTop10Movies(pageable, genreIds));
    }

    @GetMapping("/year")
    @Operation(summary = MOVIE_YEAR_SUMMARY, description = MOVIE_YEAR)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MOVIE_YEAR_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = MOVIE_YEAR_RESPONSE_404),
    })
    public ResponseEntity<List<MoviesCountByYearProjection>> moviesByYear(
            @Parameter(description = "Ano de Lançamento") @RequestParam(required = false) Integer year) {

        return ResponseEntity.ok(service.findMoviesCountByYear(year));
    }

    @GetMapping("/search")
    @Operation(summary = MOVIE_SEARCH_SUMMARY, description = MOVIE_SEARCH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MOVIE_SEARCH_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = MOVIE_SEARCH_RESPONSE_404),
    })
    public ResponseEntity<Page<Movie>> searchMovies(
            @Parameter(description = "Titulo do Filme para a Pesquisa") @RequestParam String title,
            @Parameter(description = "Número da Página") @Min(1) @RequestParam(defaultValue = PAGE_NUMBER) int page,
            @Parameter(description = "Tamanho da Página") @Min(1) @RequestParam(defaultValue = PAGE_SIZE) int size) {

        log.info("Buscando por: {}", title);
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("title"));
        return ResponseEntity.ok(service.findMoviesByTitle(title, pageRequest));
    }
}