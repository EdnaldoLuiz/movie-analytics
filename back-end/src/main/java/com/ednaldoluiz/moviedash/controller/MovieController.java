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

import com.ednaldoluiz.moviedash.docs.MovieDocs;
import com.ednaldoluiz.moviedash.dto.response.MovieResponseDTO;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.projection.MovieProjection;
import com.ednaldoluiz.moviedash.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.ednaldoluiz.moviedash.utils.ResponseUtil.*;
import static com.ednaldoluiz.moviedash.constant.APIConstants.*;

@Slf4j
@RestController
@RequestMapping(API_V1 + MOVIES)
@RequiredArgsConstructor
@Tag(name = "Controller de Buscar Filmes", description = MovieDocs.DESCRIPTION)
public class MovieController {

    private final MovieService service;

    @GetMapping(ALL)
    @Operation(summary = "Buscar todos os Filmes com Paginação", description = MovieDocs.ALL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paginação de filmes retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado"),
    })
    public ResponseEntity<Page<MovieResponseDTO>> allMovies(
            @Parameter(description = "Número da Página") @RequestParam(defaultValue = PAGE_NUMBER) int page,
            @Parameter(description = "Tamanho da Página") @RequestParam(defaultValue = PAGE_SIZE) int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("releaseDate").descending());
        log.info("Sorted as: {}.", pageable.getSort());
        return ResponseEntity.ok(service.findAllMovies(pageable));
    }

    @GetMapping("/top10")
    public ResponseEntity<Page<MovieProjection>> top10Movies(
            @Parameter(description = "Tamanho da Página") @RequestParam(defaultValue = PAGE_SIZE) int size,
            @Parameter(description = "IDs dos Gêneros") @RequestParam(defaultValue = "0") List<Long> genreIds) {

        Pageable pageable = PageRequest.of(0, size, Sort.by("voteAverage").descending());
        return ResponseEntity.ok(service.findTop10Movies(pageable, genreIds));
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar todos os Filmes com Paginação", description = MovieDocs.SEARCH)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Filmes encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado"),
    })
    public ResponseEntity<Page<Movie>> searchMovies(
            @Parameter(description = "Titulo do Filme para a Pesquisa") @RequestParam String title,
            @Parameter(description = "Número da Página") @RequestParam(defaultValue = PAGE_NUMBER) int page,
            @Parameter(description = "Tamanho da Página") @RequestParam(defaultValue = PAGE_SIZE) int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("title"));
        return ResponseEntity.ok(service.findMoviesByTitle(title, pageRequest));
    }
}
