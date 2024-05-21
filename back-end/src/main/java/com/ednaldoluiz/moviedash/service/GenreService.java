package com.ednaldoluiz.moviedash.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.ednaldoluiz.moviedash.constant.CacheConstants.*;
import com.ednaldoluiz.moviedash.repository.GenreRepository;
import com.ednaldoluiz.moviedash.repository.projection.GenreProjection;
import com.ednaldoluiz.moviedash.service.GenreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    @Cacheable(cacheNames = GENRE_CACHE + "count", key = "{#genreId}")
    public GenreProjection countByGenresId(Long genreId) {
        log.info("Contando filmes por gênero: {}", genreId);
        return genreRepository.countByGenresId(genreId);
    }

    @Cacheable(cacheNames = GENRE_CACHE + "total", key = "{'total'}")
    public Map<String, Long> countTotalGenres() {
        return genreRepository.findGenresWithMoreThanOneMovie().stream()
                .collect(Collectors.toMap(o -> (String) o[0], o -> (Long) o[1]));
    }

    @Cacheable(cacheNames = GENRE_CACHE + "popularity", key = "{'total'}")
    public List<GenreProjection> getMostPopularGenres() {
        return genreRepository.findMostPopularGenres();
    }
}
