package com.ednaldoluiz.moviedash.dto.response;

import java.time.LocalDate;

import com.ednaldoluiz.moviedash.model.Movie;
import com.fasterxml.jackson.annotation.JsonFormat;

public record MovieResponseDTO(

    String title,
    Double voteAverage,

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate releaseDate) {

        public MovieResponseDTO(Movie movie) {
            this(movie.getTitle(), movie.getVoteAverage(), movie.getReleaseDate());
        }
    }
