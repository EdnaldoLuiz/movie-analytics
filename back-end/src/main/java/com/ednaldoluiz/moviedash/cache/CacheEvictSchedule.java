package com.ednaldoluiz.moviedash.cache;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheEvictSchedule {

    private static final long HOURLY = 60 * 60 * 1000;

    @Autowired
    private CacheManager cacheManager;

    @Scheduled(fixedRate = HOURLY)
    public void evictAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            Cache cache = cacheManager.getCache(cacheName);
            if (Objects.nonNull(cache)) cache.clear();
        });
    }
}
