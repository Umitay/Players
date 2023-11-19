package com.intuit.players.config;

import com.intuit.players.service.PlayerService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@TestConfiguration
public class TestConfig {
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        // Provide a mock or test version of ReactiveMongoTemplate
        return Mockito.mock(ReactiveMongoTemplate.class);
    }


}
