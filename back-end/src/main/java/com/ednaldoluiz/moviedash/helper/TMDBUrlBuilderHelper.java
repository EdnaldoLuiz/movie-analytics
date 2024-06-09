package com.ednaldoluiz.moviedash.helper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public final class TMDBUrlBuilderHelper {

    @Value("${tmdb.api.key}")
    private String KEY;

    private static String API_KEY = "api_key";
    private static String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private static String LANGUAGE = "language";
    private static String PT_BR = "pt-BR";
    private static String GENRE = "with_genres";
    private static String PAGE = "page";

    /**
     * <h3>Constrói uma URL para consultar a API de filmes do TMDB.</h3>
     * <ul>
     *  <li>A URL inclui parâmetros de consulta para a chave da API, idioma, número da página e gêneros.</li>
     *  <li>Os gêneros são especificados como uma lista de IDs de gênero, que são convertidos em uma string separada por vírgulas.</li>
     * </ul>
     *
     * @param page o número da página para a consulta
     * @param genreId a lista de IDs de gênero para a consulta
     * @return a URL construída como uma string
     */

     public String buildUrl(Integer page, List<Long> genreId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam(API_KEY, KEY)
                .queryParam(LANGUAGE, PT_BR)
                .queryParam(PAGE, page);
    
        if (!Objects.isNull(genreId)) {
            String genres = genreId.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            builder.queryParam(GENRE, genres);
        }
    
        return builder.toUriString();
    }
}