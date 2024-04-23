package com.ednaldoluiz.moviedash.service;

import java.util.Map;

import com.ednaldoluiz.moviedash.repository.projection.GenreProjection;

public interface GenreService {

    GenreProjection countByGenresId(Long genreId);

    Map<String, Long> countTotalGenres();

}
