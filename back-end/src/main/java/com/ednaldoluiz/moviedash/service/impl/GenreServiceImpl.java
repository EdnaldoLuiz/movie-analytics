package com.ednaldoluiz.moviedash.service.impl;

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
public class GenreServiceImpl implements GenreService {
    
    private final GenreRepository genreRepository;

    @Override
    @Cacheable(cacheNames = GENRE_CACHE + "count", key = "{#genreId}")
    public GenreProjection countByGenresId(Long genreId) {
        log.info("Contando filmes por gênero: {}", genreId);
        return genreRepository.countByGenresId(genreId);
    }

    @Override
    @Cacheable(cacheNames = GENRE_CACHE + "total", key = "{'total'}")
    public Map<String, Long> countTotalGenres() {
        return convertArrayToMap(genreRepository.findGenresWithMoreThanOneMovie());
    }

    @Override
    @Cacheable(cacheNames = GENRE_CACHE + "popularity_growth", key = "{'total'}")
    public Map<String, Long> countGenresHighestPopularity(Integer currentYear, Integer previousYear) {
        log.info("Contando gêneros com maior crescimento de popularidade: {} e {}", currentYear, previousYear);
        return convertArrayToMap(genreRepository.findGenresWithHighestGrowthInPopularity(currentYear, previousYear));
    }

    @Override
    @Cacheable(cacheNames = GENRE_CACHE + "popularity", key = "{'total'}")
    public List<GenreProjection> getMostPopularGenres() {
        return genreRepository.findMostPopularGenres();
    }

    /**
     * Este método converte um array de objetos em um Map de String e Long.
     * 
     * @param objects      uma coleção de arrays de objetos vindo do repository
     *            
     * @return um Map de String e Long
     */

    private <T> Map<String, Long> convertArrayToMap(Collection<T[]> objects) {
        return objects.stream()
            .collect(
                Collectors.toMap(o -> (String) o[0], o -> (Long) o[1]));
    }
}
