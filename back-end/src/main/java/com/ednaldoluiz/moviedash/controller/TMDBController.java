package com.ednaldoluiz.moviedash.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ednaldoluiz.moviedash.docs.TMDBDocs.*;
import com.ednaldoluiz.moviedash.service.TMDBService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.ednaldoluiz.moviedash.constant.APIConstants.API_V1;
import static com.ednaldoluiz.moviedash.utils.APIUtils.PAGE_NUMBER;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = API_V1 + "tmdb", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = TMDB_CONTROLLER_NAME, description = TMDB_CONTROLLER_DESCRIPTION)
public class TMDBController {

    private final TMDBService service;

    @GetMapping("/fetch")
    @Operation(summary = TMDB_FETCH_SUMMARY, description = TMDB_FETCH_DATA)
    public ResponseEntity<Void> fetchTmdbData(
        @Parameter(description = "Numero de páginas (20 Filmes por página)") @RequestParam(defaultValue = PAGE_NUMBER) Integer pages,
        @Parameter(description = "Gêneros") @RequestParam(required = false) List<Long> genres) {
            
        log.info("Gêneros: {}", genres);
        service.fetchTmdbData(pages, genres);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    @Operation(summary = TMDB_DELETE_SUMMARY, description = TMDB_DELETE_DATA)
    public ResponseEntity<Void> deleteAllMovies(
        @Parameter(description = "Gêneros") @RequestParam(required = false) List<Long> genreIds) {
        log.info("Deletando filmes por gênero: {}", genreIds);
        service.deleteAllMovies(genreIds);
        return ResponseEntity.ok().build();
    }
}
