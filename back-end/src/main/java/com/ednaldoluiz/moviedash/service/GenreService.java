package com.ednaldoluiz.moviedash.service;

import java.util.Collection;
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
        return convertToMap(
                genreRepository.findGenresWithMoreThanOneMovie(), Long.class);
    }

    @Cacheable(cacheNames = GENRE_CACHE + "vote_average", key = "{'averageVotes'}")
    public Map<String, Double> countGenresWithHighestAverageVotes() {
        return convertToMap(
            genreRepository.findGenresWithHighestAverageVotes(), Double.class);
    }

    @Cacheable(cacheNames = GENRE_CACHE + "popularity", key = "{'popularity'}")
    public List<GenreProjection> getMostPopularGenres() {
        return genreRepository.findMostPopularGenres();
    }

    /**
     * Este método converte uma lista de array de objetos em um map de valor
     * generico.
     * 
     * @param list a lista de array de objetos
     * @param type o tipo de valor do map
     * 
     * @return um map com os valores convertidos
     */

    private <T> Map<String, T> convertToMap(Collection<Object[]> list, Class<T> type) {
        log.info("Tipo: {}", type.getSimpleName());
        return list.stream()
                .collect(Collectors.toMap(
                        object -> (String) object[0], object -> type.cast(object[1])));
    }
}