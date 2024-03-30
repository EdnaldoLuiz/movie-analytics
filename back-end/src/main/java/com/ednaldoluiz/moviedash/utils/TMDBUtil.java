package com.ednaldoluiz.moviedash.utils;

public class TMDBUtil {

    public static final String API_KEY = "api_key";
    public static final String KEY = "e4f6fca45b95d5b6b44904d806c9fe0d";
    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/top_rated";
    public static final String LANGUAGE = "language";
    public static final String PT_BR = "pt-BR";
    public static final String PAGE = "page";
    public static final String QUERY_PARAM_START = "?";
    public static final String QUERY_PARAM_SEPARATOR = "&";
    public static final String QUERY_PARAM_EQUALS = "=";

    public static final String URL = BASE_URL + QUERY_PARAM_START + API_KEY + QUERY_PARAM_EQUALS + KEY + QUERY_PARAM_SEPARATOR + LANGUAGE + QUERY_PARAM_EQUALS + PT_BR + QUERY_PARAM_SEPARATOR + PAGE + QUERY_PARAM_EQUALS;

}