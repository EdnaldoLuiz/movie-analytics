package com.ednaldoluiz.moviedash.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldoluiz.moviedash.service.TMDBService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.ednaldoluiz.moviedash.constant.APIConstants.API_V1;
import static com.ednaldoluiz.moviedash.utils.ResponseUtil.PAGE_SIZE;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(API_V1 + "tmdb")
@RequiredArgsConstructor
@Tag(name = "Autenticação de Usuários", description = "Operações de autenticação de usuários como registro e login.")
public class TMDBController {

    private final TMDBService service;

    @GetMapping("/fetch")
    @Operation(summary = "Fetch TMDB Data", description = "Fetch data from TMDB API.")
    public ResponseEntity<Void> fetchTmdbData(
        @RequestParam(defaultValue = PAGE_SIZE) int totalPages) {
            
        service.fetchTmdbData(totalPages);
        return ResponseEntity.ok().build();
    }
}
