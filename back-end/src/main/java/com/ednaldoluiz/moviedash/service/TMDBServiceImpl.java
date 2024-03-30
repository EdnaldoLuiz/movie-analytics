package com.ednaldoluiz.moviedash.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ednaldoluiz.moviedash.dto.MoviePageDTO;
import com.ednaldoluiz.moviedash.dto.MovieResponseDTO;
import com.ednaldoluiz.moviedash.model.Genre;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.GenreRepository;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
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
    public void fetchTmdbData(int totalPages) {

        for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
            String url = TMDBUtil.URL + currentPage;

            try {
                MoviePageDTO moviePage = restTemplate.getForObject(url, MoviePageDTO.class);
                List<MovieResponseDTO> results = moviePage.results();
                processResults(results);
            } catch (RestClientException e) {
                log.error("Erro na busca por dados: {}", e);
            }
        }
    }

    private void processMovie(MovieResponseDTO result) {
        log.info("Processando filme: title: {}, description: {}, releasedDate: {}",
                result.title(), result.description(), result.releaseDate());

        movieRepository.findByTitle(result.title())
        .orElseGet(() -> {
            Movie movie = new Movie(result);
            movie.genres(result.genreIds().stream()
                    .map(genreId -> genreRepository.findById(genreId)
                    .orElseGet(Genre::new)).toList());
            return movieRepository.save(movie);
        });
    }

    @Transactional
    private void processResults(List<MovieResponseDTO> results) {
        results.forEach(this::processMovie);
    }
}
