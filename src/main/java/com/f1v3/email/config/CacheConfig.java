package com.f1v3.email.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 카페인 캐시 설정 클래스
 *
 * @author 정승조
 * @version 2024. 12. 16.
 */
@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, String> cache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }
}
