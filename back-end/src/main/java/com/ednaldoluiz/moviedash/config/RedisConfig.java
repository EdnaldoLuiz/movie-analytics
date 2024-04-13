package com.ednaldoluiz.moviedash.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.ednaldoluiz.moviedash.constant.CacheConstants;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * <h3>Constrói um gerenciador de cache Redis.</h3>
     * <ul>
     *  <li>O gerenciador de cache é configurado com uma conexão de fábrica Redis e um personalizador de construtor de gerenciador de cache Redis.</li>
     *  <li>O personalizador é usado para personalizar a configuração do gerenciador de cache.</li>
     * </ul>
     *
     * @param redisConnectionFactory a conexão de fábrica Redis
     * @param customizer o personalizador de construtor de gerenciador de cache Redis
     * @return o gerenciador de cache Redis construído
     */

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
            RedisCacheManagerBuilderCustomizer customizer) {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration());
        customizer.customize(builder);
        return builder.build();
    }

    /**
     * <h3>Constrói uma configuração de cache Redis.</h3>
     * <ul>
     *  <li>A configuração de cache inclui um tempo de vida (TTL) para as entradas de cache e um serializador para os valores de cache.</li>
     * </ul>
     *
     * @return a configuração de cache Redis construída
     */

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(CacheConstants.DEFAULT_TTL))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new JdkSerializationRedisSerializer()));
    }

    /**
     * <h3>Constrói um personalizador de construtor de gerenciador de cache Redis.</h3>
     * <ul>
     *  <li>O personalizador é usado para personalizar a configuração do gerenciador de cache Redis.</li>
     *  <li>Ele define configurações de cache específicas para diferentes caches.</li>
     * </ul>
     *
     * @return o personalizador de construtor de gerenciador de cache Redis construído
     */

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(CacheConstants.MOVIE_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig()
                                .disableCachingNullValues()
                                .entryTtl(Duration.ofMinutes(CacheConstants.MOVIE_TTL)))
                .withCacheConfiguration("teste",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .disableCachingNullValues()
                                .entryTtl(Duration.ofMinutes(0)));
    }

    /**
     * <h3>Constrói um modelo Redis.</h3>
     * <ul>
     *  <li>O modelo Redis é configurado com uma conexão de fábrica Redis e um serializador para as chaves.</li>
     *  <li>Ele é usado para executar operações Redis.</li>
     * </ul>
     *
     * @param redisConnectionFactory a conexão de fábrica Redis
     * @return o modelo Redis construído
     */

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}