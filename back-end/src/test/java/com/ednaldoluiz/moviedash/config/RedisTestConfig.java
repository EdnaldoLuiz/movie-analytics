package com.ednaldoluiz.moviedash.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.ednaldoluiz.moviedash.constants.TestConstants.Redis;

import java.time.Duration;

@Testcontainers
@TestConfiguration
@SuppressWarnings("resource")
public class RedisTestConfig {

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

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(
                redisContainer.getHost(),
                redisContainer.getMappedPort(Redis.PORT)
        );
        config.setPassword(Redis.PASSWORD);
        return new LettuceConnectionFactory(config);
    }

    @Bean(name = "a")
    public <K, V> RedisTemplate<K, V> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
