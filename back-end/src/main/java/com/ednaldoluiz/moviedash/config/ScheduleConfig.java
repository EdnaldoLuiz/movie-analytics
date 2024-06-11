package com.ednaldoluiz.moviedash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ednaldoluiz.moviedash.constant.BeanConstants;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    
    @Bean(BeanConstants.SCHEDULE_TASK)
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("ScheduledTask-");
        executor.initialize();
        return executor;
    }
}
