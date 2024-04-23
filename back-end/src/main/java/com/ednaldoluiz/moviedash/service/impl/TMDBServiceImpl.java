package com.ednaldoluiz.moviedash.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ednaldoluiz.moviedash.constant.CacheConstants;
import com.ednaldoluiz.moviedash.dto.request.MoviePageDTO;
import com.ednaldoluiz.moviedash.dto.request.MovieRequestDTO;
import com.ednaldoluiz.moviedash.exception.MovieProcessingException;
import com.ednaldoluiz.moviedash.model.Genre;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.GenreRepository;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
import com.ednaldoluiz.moviedash.service.TMDBService;
import com.ednaldoluiz.moviedash.utils.TMDBUtils;

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
    @CacheEvict(
        cacheNames = CacheConstants.MOVIE_CACHE, 
        allEntries = true)
    public void fetchTmdbData(Integer totalPages, List<Long> genres) {
        log.info("Gêneros: {}", genres);

        for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
            String url = TMDBUtils.buildUrl(currentPage, genres);
            log.info("URL: {}", url);

            try {
                MoviePageDTO moviePage = restTemplate.getForObject(url, MoviePageDTO.class);
                if(Objects.nonNull(moviePage)) processResults(moviePage.results());
            } catch (RestClientException e) {
                log.error("Erro na busca por dados: {}", e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = MovieProcessingException.class)
    private void processResults(List<MovieRequestDTO> results) {
        results.forEach(this::processMovie);
    }

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
