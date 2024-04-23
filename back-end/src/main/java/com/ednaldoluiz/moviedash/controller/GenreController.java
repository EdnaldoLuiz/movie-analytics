package com.ednaldoluiz.moviedash.controller;

import static com.ednaldoluiz.moviedash.constant.APIConstants.*;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldoluiz.moviedash.docs.GenreDocs;
import com.ednaldoluiz.moviedash.repository.projection.GenreProjection;
import com.ednaldoluiz.moviedash.service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(API_V1 + GENRES)
@RequiredArgsConstructor
@Tag(name = "Controller de Operaçoes com Gêneros", description = GenreDocs.DESCRIPTION)
public class GenreController {

    private final GenreService service;
    
    @GetMapping("/count")
    @Operation(summary = "Contar a Quantidade de Filmes por Gênero", description = GenreDocs.COUNT_BY_GENRE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantidade de filmes por gênero retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado para o gênero solicitado"),
    })
    public ResponseEntity<GenreProjection> countMoviesByGenre(
            @Parameter(description = "ID do Gênero") @RequestParam Long genreId) {

        return ResponseEntity.ok(service.countByGenresId(genreId));
    }

    @GetMapping("/total")
    @Operation(summary = "Contar a Quantidade Total de Gêneros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantidade total de gêneros retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum gênero encontrado"),
    })
    public ResponseEntity<Map<String, Long>> countTotalGenres() {
        return ResponseEntity.ok(service.countTotalGenres());
    }
}
