package com.ednaldoluiz.moviedash.repository.projection.movie;

import java.io.Serializable;
import java.time.LocalDate;

import com.ednaldoluiz.moviedash.model.Movie;
import com.fasterxml.jackson.annotation.JsonFormat;

public record MovieProjection(
        String title,
        Double voteAverage,
        @JsonFormat(pattern = "yyyy-MM-dd") 
        LocalDate releaseDate
        ) implements Serializable {

    public MovieProjection(Movie movie) {
        this(movie.getTitle(), movie.getVoteAverage(), movie.getReleaseDate());
    }
}
