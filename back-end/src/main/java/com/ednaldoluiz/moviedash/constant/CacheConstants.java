package com.ednaldoluiz.moviedash.constant;

public class CacheConstants {

    private static final String SEPARATOR = "_";
    private static final String CACHE = "cache";

    public static final Integer DEFAULT_TTL = 5;
    public static final Integer MOVIE_TTL = 10;

    public static final String MOVIE = "movie";

    public static final String MOVIE_CACHE = MOVIE+SEPARATOR+CACHE+SEPARATOR;

    public static final String MOVIE_ALL = MOVIE_CACHE + "all";
    public static final String MOVIE_TOP_10 = MOVIE_CACHE + "top_10";
    public static final String MOVIE_QUERY = MOVIE_CACHE + "query";
    public static final String MOVIE_TOP_5_BY_YEAR = MOVIE_CACHE + "top_5_by_year";
    
}
