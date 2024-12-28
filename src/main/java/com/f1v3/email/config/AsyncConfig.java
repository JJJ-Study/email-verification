package com.f1v3.email.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 비동기 설정 클래스
 *
 * @author 정승조
 * @version 2024. 12. 28.
 */
@EnableAsync
@Configuration
public class AsyncConfig {

    private static final int CORE_POOL_SIZE = 10;
    private static final int AMX_POOL_SIZE = 50;
    private static final int QUEUE_CAPACITY = 20;

    /**
     * 비동기 처리를 위한 Executor 설정
     */
    @Bean
    Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(AMX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix("Async-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }
}
