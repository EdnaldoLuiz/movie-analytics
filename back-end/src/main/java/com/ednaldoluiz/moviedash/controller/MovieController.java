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
import com.ednaldoluiz.moviedash.model.enums.MovieSortType;
import com.ednaldoluiz.moviedash.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;

import java.util.List;

import lombok.RequiredArgsConstructor;

import static com.ednaldoluiz.moviedash.utils.APIUtils.*;
import static com.ednaldoluiz.moviedash.constant.APIConstants.*;

@RestController
@RequestMapping(API_V1 + MOVIES)
@RequiredArgsConstructor
@Tag(name = "Controller de Buscar Filmes", description = MovieDocs.DESCRIPTION)
public class MovieController {

    private final MovieService service;

    @GetMapping(ALL)
    @Operation(summary = "Buscar todos os Filmes com Paginação", description = MovieDocs.ALL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes paginada retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado na página solicitada"),
    })
    public ResponseEntity<Page<MovieResponseDTO>> allMovies(
            @Parameter(description = "Número da Página") @Min(1) @RequestParam(defaultValue = PAGE_NUMBER) int page,
            @Parameter(description = "Tamanho da Página") @Min(1) @RequestParam(defaultValue = PAGE_SIZE) int size,
            @Parameter(description = "Campo de Ordenação") @RequestParam(defaultValue = SORT_DEFAULT) MovieSortType sort) {
                    
        Pageable pageable = PageRequest.of(page - 1, size, getSort(sort, "ASC"));
        return ResponseEntity.ok(service.findAllMovies(pageable));
    }

    @GetMapping("/top10")
    @Operation(summary = "Buscar uma Página com o Top 10 de Filmes", description = MovieDocs.TOP_10)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Top 10 filmes retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado para os gêneros solicitados"),
    })
    public ResponseEntity<Page<MovieResponseDTO>> top10Movies(
            @Parameter(description = "IDs dos Gêneros") @RequestParam(defaultValue = "0") List<Long> genreIds) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(SORT_DEFAULT).descending());
        return ResponseEntity.ok(service.findTop10Movies(pageable, genreIds));
    }

    @GetMapping("/top5")
    @Operation(summary = "Buscar uma Página com o Top 5 Filmes por Ano", description = MovieDocs.TOP_5)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Top 5 filmes retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado para os gêneros e ano solicitado"),
    })
    public ResponseEntity<Page<MovieResponseDTO>> top5MoviesPerYear(
            @Parameter(description = "IDs dos Gêneros") @RequestParam(defaultValue = "0") List<Long> genreIds,
            @Parameter(description = "Ano de Lançamento") @RequestParam Integer year) {

        Pageable pageable = PageRequest.of(0, 5, Sort.by(SORT_DEFAULT).descending());
        return ResponseEntity.ok(service.findTop10Movies(pageable, genreIds));
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar todos os Filmes com Paginação", description = MovieDocs.SEARCH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filmes com o título solicitado encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado com o título solicitado"),
    })
    public ResponseEntity<Page<Movie>> searchMovies(
            @Parameter(description = "Titulo do Filme para a Pesquisa") @RequestParam String title,
            @Parameter(description = "Número da Página") @Min(1) @RequestParam(defaultValue = PAGE_NUMBER) int page,
            @Parameter(description = "Tamanho da Página") @Min(1) @RequestParam(defaultValue = PAGE_SIZE) int size) {

        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("title"));
        return ResponseEntity.ok(service.findMoviesByTitle(title, pageRequest));
    }
}
