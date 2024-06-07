package com.ednaldoluiz.moviedash.repository.projection.movie;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface MovieProjection {
    
    String getTitle();
    Double getVoteAverage();

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate getReleaseDate();

}
