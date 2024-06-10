package com.ednaldoluiz.moviedash.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.ednaldoluiz.moviedash.constant.CacheConstants.*;
import static com.ednaldoluiz.moviedash.utils.APIUtils.sortByValue;

import com.ednaldoluiz.moviedash.repository.GenreRepository;
import com.ednaldoluiz.moviedash.repository.projection.genre.GenreProjection;
import com.ednaldoluiz.moviedash.repository.projection.genre.PopularMoviesByGenreProjection;
import com.ednaldoluiz.moviedash.service.GenreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreService extends AbstractService {

    private final GenreRepository genreRepository;

    @Cacheable(cacheNames = GENRE_CACHE + "count", key = "{#genreId}")
    public GenreProjection countByGenresId(Long genreId) {
        log.info("Contando filmes por gênero: {}", genreId);
        return genreRepository.countByGenresId(genreId);
    }

    @Cacheable(cacheNames = GENRE_CACHE + "total", key = "{'total'}")
    public Map<String, Long> countTotalGenres() {
        Map<String, Long> count = convertToMap(genreRepository.findGenresWithMoreThanOneMovie(), Long.class);
        return sortByValue(count);
    }

    @Cacheable(cacheNames = GENRE_CACHE + "vote_average", key = "{'averageVotes'}")
    public Map<String, Double> countGenresWithHighestAverageVotes() {
        Map<String, Double> averageVotes = convertToMap(genreRepository.findGenresWithHighestAverageVotes(), Double.class);
        return sortByValue(averageVotes);
    }

    @Cacheable(cacheNames = GENRE_CACHE + "popularity", key = "{'popularity'}")
    public List<GenreProjection> getMostPopularGenres() {
        return genreRepository.findMostPopularGenres();
    }

    @Cacheable(cacheNames = GENRE_CACHE + "popular_movies", key = "{#genre}")
    public List<PopularMoviesByGenreProjection> getMostPopularMoviesByGenre(Long genre) {
        log.info("Buscando filmes mais populares por gênero: {}", genre);
        return genreRepository.findMostPopularMoviesByGenre(genre);
    }
}