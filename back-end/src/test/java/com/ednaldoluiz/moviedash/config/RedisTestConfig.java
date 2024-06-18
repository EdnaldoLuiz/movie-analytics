package com.ednaldoluiz.moviedash.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.ednaldoluiz.moviedash.constants.TestConstants.Redis;

import java.time.Duration;

@EnableCaching
@Testcontainers
@TestConfiguration
@SuppressWarnings("resource")
public class RedisCacheTestConfig {

    @Container
    private static final GenericContainer<?> redisContainer = new GenericContainer<>(
            DockerImageName.parse(Redis.VERSION))
            .withExposedPorts(Redis.PORT)
            .withCommand("redis-server --requirepass " + Redis.PASSWORD)
            .waitingFor(Wait.forLogMessage(".*Ready to accept connections.*\\s", 1))
            .withStartupTimeout(Duration.ofMinutes(2))
            .withReuse(false);

    static {
        redisContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.redis.host", redisContainer::getHost);
        dynamicPropertyRegistry.add("spring.redis.port", () -> redisContainer.getMappedPort(Redis.PORT));
        dynamicPropertyRegistry.add("spring.redis.password", () -> Redis.PASSWORD);
    }

    @Bean
    public GenericContainer<?> redisContainer() {
        return redisContainer;
    }
}
