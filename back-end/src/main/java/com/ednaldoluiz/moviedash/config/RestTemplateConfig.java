package com.ednaldoluiz.moviedash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.ednaldoluiz.moviedash.constant.BeanConstants;

@Configuration
public class RestTemplateConfig {

    @Bean(BeanConstants.REST_TEMPLATE)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}