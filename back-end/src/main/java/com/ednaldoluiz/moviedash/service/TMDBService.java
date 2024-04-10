package com.ednaldoluiz.moviedash.service;

import java.util.List;

public interface TMDBService {

    void fetchTmdbData(Integer totalPages,  List<Long> genreId);
    
}