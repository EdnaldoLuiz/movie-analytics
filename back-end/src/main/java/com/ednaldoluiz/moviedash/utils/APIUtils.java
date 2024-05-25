package com.ednaldoluiz.moviedash.utils;

import org.springframework.data.domain.Sort;

import com.ednaldoluiz.moviedash.model.enums.MovieSortType;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class APIUtils {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static final String[] FILE_HEADER = { "ID", "Título", "Conteúdo adulto", "Idioma", "Data de Lançamento",
            "Popularidade",
            "Nota Média", "Quantidade de Votos", "Gêneros" };

    public static final String ATTACHMENT = "attachment; filename=";
    public static final String PAGE_NUMBER = "1";
    public static final String PAGE_SIZE = "10";
    public static final String SORT_DEFAULT = "voteAverage";

    public static Sort getSort(MovieSortType sort, String direction) {
        return direction.equalsIgnoreCase("ASC") ? Sort.by(sort.getValue()).ascending()
                : Sort.by(sort.getValue()).descending();
    }
}
