package com.ednaldoluiz.moviedash.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.projection.movie.MovieProjection;
import com.fasterxml.jackson.annotation.JsonFormat;

public record MovieResponseDTO (

    String title,
    Double voteAverage,

    @JsonFormat(pattern = "yyyy/MM/dd")
    LocalDate releaseDate) implements Serializable {

        public MovieResponseDTO(Movie movie) {
            this(movie.getTitle(), movie.getVoteAverage(), movie.getReleaseDate());
        }

        public MovieResponseDTO(MovieProjection movie) {
            this(movie.getTitle(), movie.getVoteAverage(), movie.getReleaseDate());
        }
    }
