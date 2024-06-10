package com.ednaldoluiz.moviedash.model.enums;

import lombok.Getter;

@Getter
public enum GenreType {

    ALL(0L),
    ACTION(28L),
    ADVENTURE(12L),
    ANIMATION(16L),
    COMEDY(35L),
    CRIME(80L),
    DOCUMENTARY(99L),
    DRAMA(18L),
    FAMILY(10751L),
    FANTASY(14L),
    HISTORY(36L),
    HORROR(27L),
    MUSIC(10402L),
    MYSTERY(9648L),
    ROMANCE(10749L),
    SCIENCE_FICTION(878L),
    TV_MOVIE(10770L),
    THRILLER(53L),
    WAR(10752L),
    WESTERN(37L);

    private final Long value;

    GenreType(Long value) {
        this.value = value;
    }

    public static GenreType fromValue(Long value) {
        for (GenreType genreType : GenreType.values()) {
            if (genreType.value == value) {
                return genreType;
            }
        }
        return null;
    }
}
