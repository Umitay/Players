package com.intuit.players.controller;

import com.intuit.players.config.TestConfig;
import com.intuit.players.model.Address;
import com.intuit.players.model.FIO;
import com.intuit.players.model.Player;
import com.intuit.players.repository.IReactiveMongoRepository;
import com.intuit.players.service.PlayerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@WebFluxTest(controllers = PlayerController.class)
@AutoConfigureWebTestClient
class PlayerControllerTest {

    @MockBean
    private PlayerController playerController;

    @MockBean
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private WebTestClient webClient;


    @Test
    void getAllPlayers() {
        webClient.get().uri("/api/players")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Player.class)
                .getResponseBody()
                .as(StepVerifier::create)// Convert the Flux to StepVerifier
                .verifyComplete();
    }

    @Test
    void getPlayerById() {

        String playerId = "abbated01";

        //TODO Use StepVerifier to verify the reactive behavior
        webClient.get().uri("/api/players/{playerId}", playerId)
                .exchange()
                .expectStatus().isOk();
    }
}