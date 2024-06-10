package com.ednaldoluiz.moviedash.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.ednaldoluiz.moviedash.model.Genre;
import com.ednaldoluiz.moviedash.model.Movie;

public record MovieExportDTO(
    Long id,
    String title,
    Boolean adult,
    String language,
    Double popularity,
    LocalDate releaseDate,
    Boolean video,
    Double voteAverage,
    Integer voteCount,
    String genres
) {
    public MovieExportDTO(Movie movie) {
        this(
            movie.getId(),
            movie.getTitle(),
            movie.getAdult(),
            movie.getLanguage(),
            movie.getPopularity(),
            movie.getReleaseDate(),
            movie.getVideo(),
            movie.getVoteAverage(),
            movie.getVoteCount(),
            movie.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.joining(";"))
        );
    }

    public String[] toArray() {
        return new String[] {
            String.valueOf(id),
            title,
            String.valueOf(adult),
            language,
            releaseDate.toString(),
            String.valueOf(popularity),
            String.valueOf(voteAverage),
            String.valueOf(voteCount),
            genres
        };
    }
}
