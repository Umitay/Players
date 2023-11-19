package com.intuit.players.repository;

import com.intuit.players.model.Address;
import com.intuit.players.model.FIO;
import com.intuit.players.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@TestPropertySource(properties = {
        "spring.data.mongodb.uri=mongodb://umitay:secret@localhost:27017/players?authSource=admin"
})
class IReactiveMongoRepositoryTest {
        @Autowired
        private  IReactiveMongoRepository playerRepository;
        @Autowired
        private ReactiveMongoTemplate reactiveMongoTemplate;

        @Test
        void existingPayerCanBeFound() {

            Player player = Player.builder()
                    .playerID("65656")
                    .birthAddress(Address.builder().country("").state("").city("").build())
                    .fullName(FIO.builder().name("er").nameLast("rrr").nameGiven("eeee").build())
                    .retroID("22222")
                    .bbrefID("11111").build();

            // Save the player using ReactiveMongoTemplate
            Mono<Player> savedPlayerMono = reactiveMongoTemplate.save(player);
            StepVerifier.create(savedPlayerMono)
                    .expectNextCount(1)
                    .verifyComplete();

            /*
            OR  Block to get the saved player
            Player savedPlayer = savedPlayerMono.block();
            Assertions.assertNotNull(savedPlayer);
             */


            // Retrieve the player using playerRepository
            Mono<Player> retrievedPlayerMono = savedPlayerMono.flatMap(savedPlayer ->
                    playerRepository.findById(savedPlayer.getPlayerID())
            );
            StepVerifier.create(retrievedPlayerMono)
                    .expectNextMatches(retrievedPlayer ->
                            retrievedPlayer.getPlayerID().equals(player.getPlayerID()))
                    .verifyComplete();

            /*
            OR Block to get the retrieved player
            Assertions.assertNotNull(retrievedPlayerMono.blockOptional().orElse(null));
           */

            Assertions.assertEquals(player.getPlayerID(), retrievedPlayerMono.map(Player::getPlayerID).blockOptional().orElse(null));
        }
}