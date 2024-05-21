package com.ednaldoluiz.moviedash.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.ednaldoluiz.moviedash.constant.CacheConstants.*;
import com.ednaldoluiz.moviedash.dto.response.MovieResponseDTO;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
import com.ednaldoluiz.moviedash.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@CacheConfig(cacheNames = MOVIE_CACHE)
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    @Cacheable(
        cacheNames = "all", 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieResponseDTO> findAllMovies(Pageable pageable) {
        log.info("Total de filmes: {}", repository.count());
        return convertPage(repository.findAll(pageable), MovieResponseDTO::new);
    }

    @Cacheable(
        cacheNames = "top10", 
        key = "{#pageable.pageNumber,#pageable.pageSize,#pageable.sort,#genreIds}", 
        unless = "#result.numberOfElements < 5")
    public Page<MovieResponseDTO> findTop10Movies(Pageable pageable, List<Long> genreIds) {
        log.info("Gêneros: {}", genreIds);
        return convertPage(repository.findTop10ByGenreAndVoteAverage(pageable, genreIds), MovieResponseDTO::new);
    }

    @Cacheable(
        cacheNames = "top5", 
        key = "{#genreIds, #year}", 
        unless = "#result.numberOfElements == 5")
    public Page<MovieResponseDTO> findTop5MoviesByYear(Pageable pageable, List<Long> genreIds, Integer year) {
        log.info("Gêneros: {}, Ano: {}", genreIds, year);
        return convertPage(repository.findTop5ByGenreAndVoteAverageInYear(pageable, genreIds, year), MovieResponseDTO::new);
    }

    public Page<Movie> findMoviesByTitle(String keyword, Pageable pageable) {
        log.info("Buscando por: {}", keyword);
        return repository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    /**
     * Este método converte uma página de projeções em uma página de DTOs de forma generica.
     * 
     * @param page      a página de projeções
     * @param converter a função de conversão de projeção para DTO  
     *            
     * @return a página de DTOs
     */

    private <PROJECTION, DTO> Page<DTO> convertPage(Page<PROJECTION> page, Function<PROJECTION, DTO> converter) {
        List<DTO> content = page.getContent()
            .stream()
            .map(converter)
            .toList();
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
