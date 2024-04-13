package com.ednaldoluiz.moviedash.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ednaldoluiz.moviedash.dto.request.MoviePageDTO;
import com.ednaldoluiz.moviedash.dto.request.MovieRequestDTO;
import com.ednaldoluiz.moviedash.model.Genre;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.GenreRepository;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
import com.ednaldoluiz.moviedash.service.TMDBService;
import com.ednaldoluiz.moviedash.utils.TMDBUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TMDBServiceImpl implements TMDBService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final RestTemplate restTemplate;

    @Override
    @CacheEvict(value = {"movies"}, allEntries = true)
    public void fetchTmdbData(Integer totalPages, List<Long> genres) {
        log.info("Gêneros: {}", genres);
        for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
            String url = TMDBUtil.buildUrl(currentPage, genres);
            log.info("URL: {}", url);

            try {
                MoviePageDTO moviePage = restTemplate.getForObject(url, MoviePageDTO.class);
                List<MovieRequestDTO> results = Objects.requireNonNull(moviePage.results());
                processResults(results);
            } catch (RestClientException e) {
                log.error("Erro na busca por dados: {}", e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private void processResults(List<MovieRequestDTO> results) {
        results.forEach(this::processMovie);
    }

    @CachePut(value = "movies", key = "#result.id")
    private void processMovie(MovieRequestDTO result) {
        log.info("Processando filme: title: {}, releasedDate: {}, genres: {}",
                result.title(), result.releaseDate(), result.genreIds());

        movieRepository.findByTitle(result.title())
        .orElseGet(() -> {
            Movie movie = new Movie(result);
            movie.setGenres(result.genreIds().stream()
                    .map(genreId -> genreRepository.findById(genreId)
                    .orElseGet(Genre::new)).toList());
            return movieRepository.save(movie);
        });
    }
}
