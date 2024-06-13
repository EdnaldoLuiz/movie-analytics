package com.ednaldoluiz.moviedash.constant;

public interface APIConstants {

    String SUFFIX = "/";
    String API = "api";
    String V1 = "v1";
    String API_V1 = SUFFIX + API + SUFFIX + V1 + SUFFIX;

    interface Movie {
        String MOVIES = "movies";
        String ALL = "all";
        String TOP10 = "top10";
        String TOP5 = "top5";
        String YEAR = "year";
        String SEARCH = "search";
    }

    interface Genre {
        String GENRES = "genres";
        String COUNT = "count";
        String TOTAL = "total";
        String VOTE_AVERAGE = "vote-average";
        String POPULAR_GENRES = "popular-genres";
        String POPULAR_MOVIES = "popular-movies";
    }

    interface Tmdb {
        String TMDB = "tmdb";
        String FETCH = "fetch";
        String DELETE = "delete";
    }

    interface FileExport {
        String FILE_EXPORT = "file-export";
        String EXPORT = "export";
    }
}