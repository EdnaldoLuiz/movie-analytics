package com.ednaldoluiz.moviedash.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.ednaldoluiz.moviedash.constants.TestConstants.Postgres;

import java.time.Duration;

import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;

@Testcontainers
@TestConfiguration
@SuppressWarnings("resource")
public class PostgresDatabaseTestConfig {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(Postgres.VERSION)
            .withDatabaseName(Postgres.DATABASE_NAME)
            .withExposedPorts(Postgres.PORT)
            .withUsername(Postgres.USERNAME)
            .withPassword(Postgres.PASSWORD)
            .withInitScript(Postgres.INIT_SCRIPT)
            .waitingFor(new HostPortWaitStrategy())
            .waitingFor(new LogMessageWaitStrategy()
                .withRegEx(".*database system is ready to accept connections.*\\s")
                .withTimes(2)
                .withStartupTimeout(Duration.ofMinutes(2)));;

    static {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Bean
    public PostgreSQLContainer<?> postgreSQLContainer() {
        return postgreSQLContainer;
    }
}