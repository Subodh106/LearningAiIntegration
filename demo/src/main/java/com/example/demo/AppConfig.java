package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public RestClient.Builder restClientBuilder(){
        return RestClient.builder();
    }
}
