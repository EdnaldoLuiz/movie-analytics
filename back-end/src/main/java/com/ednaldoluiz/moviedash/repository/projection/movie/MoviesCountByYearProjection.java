package com.ednaldoluiz.moviedash.repository.projection.movie;

import java.io.Serializable;

public record MoviesCountByYearProjection(
        Integer year,
        Long movieCount
) implements Serializable {}