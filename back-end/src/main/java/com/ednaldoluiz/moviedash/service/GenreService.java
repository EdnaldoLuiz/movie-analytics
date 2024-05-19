package com.ednaldoluiz.moviedash.service;

import java.util.List;
import java.util.Map;

import com.ednaldoluiz.moviedash.repository.projection.GenreProjection;

public interface GenreService {

    GenreProjection countByGenresId(Long genreId);

    List<GenreProjection> getMostPopularGenres();

    Map<String, Long> countTotalGenres();

}
