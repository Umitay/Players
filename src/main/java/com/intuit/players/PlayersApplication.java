package com.intuit.players;

import com.intuit.players.repository.IMongoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.intuit.players.repository")
public class PlayersApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayersApplication.class, args);
    }

}
