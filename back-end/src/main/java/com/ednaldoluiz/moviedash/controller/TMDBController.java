package com.ednaldoluiz.moviedash.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldoluiz.moviedash.docs.TMDBDocs;
import com.ednaldoluiz.moviedash.service.TMDBService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.ednaldoluiz.moviedash.constant.APIConstants.API_V1;
import static com.ednaldoluiz.moviedash.utils.ResponseUtil.PAGE_NUMBER;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = API_V1 + "tmdb", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Controller do TMDB", description = TMDBDocs.DESCRIPTION)
public class TMDBController {

    private final TMDBService service;

    @GetMapping("/fetch")
    @Operation(summary = "Salvar filmes do TMDB", description = TMDBDocs.FETCH_TMDB_DATA)
    public ResponseEntity<Void> fetchTmdbData(
        @Parameter(description = "Numero de páginas") @RequestParam(defaultValue = PAGE_NUMBER) Integer pages,
        @Parameter(description = "Gêneros") @RequestParam(defaultValue = "0") List<Long> genres) {
            
        log.info("Gêneros: {}", genres);
        service.fetchTmdbData(pages, genres);
        return ResponseEntity.ok().build();
    }
}
