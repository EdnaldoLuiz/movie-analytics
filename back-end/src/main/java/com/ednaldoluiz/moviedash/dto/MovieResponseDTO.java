package com.ednaldoluiz.moviedash.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieResponseDTO (

    Long id,
    Boolean adult,
    Double popularity,
    String title,
    Boolean video,

    @JsonProperty("overview")
    String description,
    
    @JsonProperty("vote_count")
    Integer voteCount,

    @JsonProperty("vote_average")
    Double voteAverage,

    @JsonProperty("release_date")
    LocalDate releaseDate,

    @JsonProperty("poster_path")
    String poster,

    @JsonProperty("original_language")
    String language,

    @JsonProperty("genre_ids")
    List<Long> genreIds

) implements Serializable {}