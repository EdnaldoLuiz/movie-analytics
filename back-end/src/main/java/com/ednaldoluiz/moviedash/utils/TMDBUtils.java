package com.ednaldoluiz.moviedash.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TMDBUtils {

    public static final String API_KEY = "api_key";
    public static final String KEY = "e4f6fca45b95d5b6b44904d806c9fe0d";
    public static final String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    public static final String LANGUAGE = "language";
    public static final String PT_BR = "pt-BR";
    public static final String GENRE = "with_genres";
    public static final String PAGE = "page";

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

    public static String buildUrl(Integer page, List<Long> genreId) {
        String genres = genreId.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam(API_KEY, KEY)
                .queryParam(LANGUAGE, PT_BR)
                .queryParam(PAGE, page)
                .queryParam(GENRE, genres)
                .toUriString();
    }
}