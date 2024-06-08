package com.ednaldoluiz.moviedash.repository.projection.genre;

import java.io.Serializable;

public record GenreProjection(
        String name,
        Long quantity,
        Long total
        ) implements Serializable {}