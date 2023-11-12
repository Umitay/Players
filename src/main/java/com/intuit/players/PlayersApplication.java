package com.intuit.players;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
//@CrossOrigin(origins = "http://localhost:8081")
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.intuit.players.repository")
public class PlayersApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayersApplication.class, args);
    }

}
