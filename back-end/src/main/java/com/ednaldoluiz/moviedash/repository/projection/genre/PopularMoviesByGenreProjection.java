package com.ednaldoluiz.moviedash.repository.projection.genre;

import java.io.Serializable;

public record PopularMoviesByGenreProjection(
        String genre,
        Long id,
        String title,
        Double popularity
) implements Serializable {}