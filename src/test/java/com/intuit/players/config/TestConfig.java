package com.intuit.players.config;

import com.intuit.players.controller.PlayerControllerTest;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.config.EnableWebFlux;

@TestConfiguration
public class TestConfig {
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        // Provide a mock or test version of ReactiveMongoTemplate
        return Mockito.mock(ReactiveMongoTemplate.class);
    }


}
