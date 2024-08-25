package com.ednaldoluiz.moviedash.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.ednaldoluiz.moviedash.model.Genre;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.repository.GenreRepository;
import com.ednaldoluiz.moviedash.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.ednaldoluiz.moviedash.dto.request.MoviePageDTO;
import com.ednaldoluiz.moviedash.dto.request.MovieRequestDTO;
import com.ednaldoluiz.moviedash.helper.TMDBUrlBuilderHelper;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TMDBServiceTest {

    // private static final int PAGE_SIZE = 20;
    // private static final String TMDB_URL = "https://api.themoviedb.org/3/discover/movie?api_key=test_key&language=pt-BR&page=1&with_genres=28,35";

    // @Autowired
    // private TMDBService tmdbService;

    // @MockBean
    // private MovieRepository movieRepository;

    // @MockBean
    // private GenreRepository genreRepository;

    // @MockBean
    // private RestTemplate restTemplate;

    // @MockBean
    // private TMDBUrlBuilderHelper urlBuilder;

    // @BeforeEach
    // void setUp() {
    //     MockitoAnnotations.openMocks(this);
    //     setupGenreRepository();
    //     setupRestTemplate();
    // }

    // @Test
    // @Order(1)
    // @DisplayName("Teste de verificar quantidade de filmes salvos")
    // void testSaveMoviesFromTmdbData() {
    //     tmdbService.fetchTmdbData(2, List.of(28L, 35L));
    //     Mockito.verify(movieRepository, Mockito.times(PAGE_SIZE * 2)).save(Mockito.any(Movie.class));
    // }

    // @Test
    // @Order(2)
    // @DisplayName("Teste de exceções ao buscar dados da API do TMDB")
    // void test_FetchTmdbData_WithException() {
    //     Mockito.when(urlBuilder.buildUrl(Mockito.anyInt(), Mockito.anyList()))
    //             .thenReturn(TMDB_URL);
    //     Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(MoviePageDTO.class)))
    //             .thenThrow(new RestClientException("Falha na comunicação"));

    //     assertDoesNotThrow(() -> tmdbService.fetchTmdbData(1, List.of(28L, 35L)),
    //             "O serviço deve lidar corretamente com exceções ao buscar dados da API do TMDB sem lançar exceções para o chamador.");
    // }

    // @Test
    // @Order(3)
    // @DisplayName("Teste de deletar todos os filmes sem especificar gêneros")
    // void testDeleteAllMoviesWithoutGenres() {
    //     tmdbService.deleteAllMovies(List.of());
    //     Mockito.verify(movieRepository).deleteAll();
    //     assertTrue(movieRepository.findAll().isEmpty(), "O repositório deve estar vazio após deletar todos os filmes");
    // }

    // @Test
    // @Order(4)
    // @DisplayName("Teste de deletar todos os filmes por gênero")
    // void testDeleteAllMoviesByGenre() {
    //     List<Long> genreIds = List.of(1L, 2L);
    //     tmdbService.deleteAllMovies(genreIds);
    //     Mockito.verify(movieRepository).deleteAllByGenresIdIn(genreIds);
    //     assertTrue(movieRepository.findAll().isEmpty(), "O repositório deve estar vazio após deletar todos os filmes");
    // }

    // @Test
    // @Order(5)
    // @DisplayName("Teste de deletar filmes por um único gênero e verificar se filmes de outros gêneros permanecem")
    // void testDeleteMoviesByOneGenreAndCheckOthersRemain() {
    //     setupMoviesInRepositoryWithGenres(List.of(1L, 2L, 3L));
    //     tmdbService.deleteAllMovies(List.of(1L));
    //     assertFalse(movieRepository.findAll().isEmpty(),
    //             "O repositório não deve estar vazio após deletar filmes de um único gênero");
    //     long remainingMoviesCount = movieRepository.count();
    //     assertNotEquals(0, remainingMoviesCount, "Deve haver filmes de outros gêneros no repositório após a deleção");
    // }

    // private void setupGenreRepository() {
    //     Genre genre1 = new Genre();
    //     genre1.setId(1L);
    //     genre1.setName("Action");
    //     Genre genre2 = new Genre();
    //     genre2.setId(2L);
    //     genre2.setName("Comedy");
    //     Mockito.when(genreRepository.findById(1L)).thenReturn(Optional.of(genre1));
    //     Mockito.when(genreRepository.findById(2L)).thenReturn(Optional.of(genre2));
    // }

    // private void setupMoviesInRepositoryWithGenres(List<Long> genreIds) {
    //     genreIds.forEach(genreId -> {
    //         Genre genre = genreRepository.findById(genreId).orElseGet(() -> {
    //             Genre newGenre = new Genre();
    //             newGenre.setId(genreId);
    //             return genreRepository.save(newGenre);
    //         });
    
    //         Movie movie = new Movie();
    //         movie.setGenres(Collections.singletonList(genre));
    //         movieRepository.save(movie);
    //     });
    // }

    // private void setupRestTemplate() {
    //     Mockito.when(urlBuilder.buildUrl(Mockito.anyInt(), Mockito.anyList())).thenReturn(TMDB_URL);

    //     List<MovieRequestDTO> movieRequestsPage1 = createMovieRequestDTOs(20);
    //     List<MovieRequestDTO> movieRequestsPage2 = createMovieRequestDTOs(20);

    //     MoviePageDTO moviePageDTO1 = new MoviePageDTO(1, movieRequestsPage1);
    //     MoviePageDTO moviePageDTO2 = new MoviePageDTO(2, movieRequestsPage2);

    //     Mockito.when(restTemplate.getForObject(TMDB_URL, MoviePageDTO.class))
    //             .thenReturn(moviePageDTO1)
    //             .thenReturn(moviePageDTO2);
    // }

    // private List<MovieRequestDTO> createMovieRequestDTOs(int count) {
    //     return IntStream.range(0, count)
    //             .mapToObj(i -> new MovieRequestDTO(
    //                     (long) i,
    //                     false,
    //                     7.5 + i,
    //                     "Movie Title " + i,
    //                     false,
    //                     "Movie description " + i,
    //                     1000 + i,
    //                     8.0 + i,
    //                     LocalDate.now(),
    //                     "/poster/path" + i,
    //                     "en",
    //                     List.of(1L, 2L)))
    //             .collect(Collectors.toList());
    // }
}