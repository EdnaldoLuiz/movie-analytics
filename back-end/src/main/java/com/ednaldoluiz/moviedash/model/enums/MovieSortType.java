package com.ednaldoluiz.moviedash.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovieSortType {

    VOTE_AVERAGE("voteAverage"),
    RELEASE_DATE("releaseDate"),
    VOTE_COUNT("voteCount"),
    POPULARITY("popularity");
    
    private String value;

}
