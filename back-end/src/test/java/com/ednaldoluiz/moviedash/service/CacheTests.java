package com.ednaldoluiz.moviedash.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CacheTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testStoreDataInCache() {
        String key = "testKey";
        String value = "testValue";

        redisTemplate.opsForValue().set(key, value);
        String cachedValue = (String) redisTemplate.opsForValue().get(key);

        assertThat(cachedValue).isEqualTo(value);
    }

    @Test
    void testRetrieveDataFromCache() {
        String key = "testKey";
        String value = "testValue";

        redisTemplate.opsForValue().set(key, value);
        String cachedValue = (String) redisTemplate.opsForValue().get(key);

        assertThat(cachedValue).isEqualTo(value);
    }

    @Test
    void testDeleteDataFromCache() {
        String key = "testKey";
        String value = "testValue";

        redisTemplate.opsForValue().set(key, value);
        redisTemplate.delete(key);
        String cachedValue = (String) redisTemplate.opsForValue().get(key);

        assertThat(cachedValue).isNull();
    }
}
