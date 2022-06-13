package com.pabloriosramirez.api.rickandmorty;

import com.pabloriosramirez.api.rickandmorty.interceptor.ClientLoggingInterceptor;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ImportResource("classpath:integration.cfg.xml")
@OpenAPIDefinition(info = @Info(title = "Rick and MortyAPI", version = "1.0.0"))
public class RickAndMortyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RickAndMortyApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ClientLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;

        /*
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofHours(1))
                .setReadTimeout(Duration.ofHours(1))
                .build();*/
    }

}
