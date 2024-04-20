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
     * Este método cria um gerenciador de cache Redis personalizado.
     * Primeiro, ele cria um construtor de gerenciador de cache Redis usando a
     * fábrica de conexões Redis fornecida.
     * Em seguida, ele define a configuração padrão do cache usando o método
     * cacheConfiguration().
     * Finalmente, ele usa o personalizador fornecido para fazer ajustes adicionais
     * no construtor antes de construir o gerenciador de cache.
     *
     * @param redisConnectionFactory a fábrica de conexões Redis
     * @param customizer             o personalizador para o construtor do
     *                               gerenciador de cache
     * @return o gerenciador de cache Redis
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
     * Este método cria uma configuração de cache padrão.
     * Ele começa com a configuração de cache padrão do Redis e faz algumas
     * modificações:
     * Define um tempo de vida (TTL) para as entradas do cache para 10 minutos.
     * Define um serializador para os valores do cache usando
     * JdkSerializationRedisSerializer, que serializa objetos Java para bytes.
     *
     * @return a configuração de cache
     */

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(10))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new JdkSerializationRedisSerializer()));
    }

    /**
     * Este método cria um personalizador para o construtor do gerenciador de cache
     * Redis.
     * O personalizador define configurações de cache específicas para diferentes
     * tipos de cache.
     * Por exemplo, para o tipo de cache "UPDATED_OBJECT", ele desativa o cache de
     * valores nulos e define um TTL de 20 minutos.
     * Para o tipo de cache "teste", ele desativa o cache de valores nulos e define
     * um TTL de 10 minutos.
     *
     * @return o personalizador
     */

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(CacheConstants.MOVIE_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig()
                                .disableCachingNullValues()
                                .entryTtl(Duration.ofHours(1)))
                .withCacheConfiguration(CacheConstants.GENRE_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig()
                                .disableCachingNullValues()
                                .entryTtl(Duration.ofMinutes(40)));
    }

    /**
     * Este método cria um modelo Redis.
     * Primeiro, ele cria um novo modelo Redis e define o serializador padrão para
     * StringRedisSerializer, que serializa strings para bytes.
     * Em seguida, ele define a fábrica de conexões do modelo para a fábrica de
     * conexões Redis fornecida.
     * Depois disso, ele define o serializador de chave do modelo para
     * StringRedisSerializer.
     * Finalmente, ele chama afterPropertiesSet() para inicializar o modelo.
     *
     * @param cf a fábrica de conexões Redis
     * @return o modelo Redis
     */

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(cf);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}