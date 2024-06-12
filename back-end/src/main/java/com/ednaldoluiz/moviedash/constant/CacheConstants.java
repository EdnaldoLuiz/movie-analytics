package com.ednaldoluiz.moviedash.constant;

public interface CacheConstants {

    String SEPARATOR = "_";
    String CACHE = "cache";
    String MOVIE = "movie";
    String GENRE = "genre";

    interface Movie {
        String MOVIE_CACHE_ALL = MOVIE + SEPARATOR + CACHE + SEPARATOR + "all";
        String MOVIE_CACHE_TOP10 = MOVIE + SEPARATOR + CACHE + SEPARATOR + "top10";
        String MOVIE_CACHE_TOP5 = MOVIE + SEPARATOR + CACHE + SEPARATOR + "top5";
        String MOVIE_CACHE_YEAR = MOVIE + SEPARATOR + CACHE + SEPARATOR + "year";
    }

    interface Genre {
        String GENRE_CACHE_COUNT = GENRE + SEPARATOR + CACHE + SEPARATOR + "count";
        String GENRE_CACHE_TOTAL = GENRE + SEPARATOR + CACHE + SEPARATOR + "total";
        String GENRE_CACHE_VOTE_AVERAGE = GENRE + SEPARATOR + CACHE + SEPARATOR + "vote_average";
        String GENRE_CACHE_POPULARITY = GENRE + SEPARATOR + CACHE + SEPARATOR + "popularity";
        String GENRE_CACHE_POPULAR_MOVIES = GENRE + SEPARATOR + CACHE + SEPARATOR + "popular_movies";
    }
}
