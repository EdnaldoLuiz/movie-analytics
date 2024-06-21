package com.ednaldoluiz.moviedash;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.ednaldoluiz.moviedash.config.PostgresTestConfig;
import com.ednaldoluiz.moviedash.config.RedisTestConfig;
import com.ednaldoluiz.moviedash.repository.GenreRepository;
import com.ednaldoluiz.moviedash.repository.MovieRepository;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoviedashApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @Order(value = 1)
    @DisplayName("Testar o carregamento do contexto.")
    void contextLoads() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    @Order(value = 2)
    @DisplayName("Testar conexão com o banco de dados.")
    void testConnectionToDatabase() {
        Assertions.assertNotNull(movieRepository);
        Assertions.assertNotNull(genreRepository);
    }
}
