package com.ednaldoluiz.moviedash.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.ednaldoluiz.moviedash.dto.MovieResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean adult;
    private String poster;

    @Column(name = "original_language")
    private String language;
    private String title;
    private String description;
    private Double popularity;
    private LocalDate releaseDate;
    private Boolean video;
    private Double voteAverage;
    private Integer voteCount;

    @ManyToMany
    @JoinTable(
        name = "movie_genres",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    public Movie(MovieResponseDTO result) {
        this.adult = result.adult();
        this.popularity = result.popularity();
        this.title = result.title();
        this.video = result.video();
        this.description = result.description();
        this.voteCount = result.voteCount();
        this.voteAverage = result.voteAverage();
        this.releaseDate = result.releaseDate();
        this.poster = result.poster();
        this.language = result.language();
    }
}