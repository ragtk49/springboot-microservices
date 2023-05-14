package com.venkatagollapudi.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced // This annotation is used to make the WebClient load balanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
}
