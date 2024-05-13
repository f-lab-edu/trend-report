package com.trendreport.external.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final String BASE_URL = "https://openapi.naver.com/v1/datalab";
    @Value("${naver.client-id}")
    private String CLIENT_ID;
    @Value("${naver.client-secret}")
    private String CLIENT_SECRET;

    @Bean
    public ReactorResourceFactory resourceFactory(){
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        return factory;
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
            .baseUrl((BASE_URL))
            .defaultHeaders(httpHeaders -> {
                httpHeaders.add("X-Naver-Client-Id", CLIENT_ID);
                httpHeaders.add("X-Naver-Client-Secret", CLIENT_SECRET);
                httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");
            })
            .build();
    }
}
