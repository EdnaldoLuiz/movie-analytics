package com.ednaldoluiz.moviedash.repository.projection.genre;

import java.io.Serializable;

public record GenreProjection(
        String name,
        Integer quantity,
        Integer total
        ) implements Serializable {}