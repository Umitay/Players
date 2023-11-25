package com.intuit.players.controller;

import com.intuit.players.model.Player;
import com.intuit.players.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@WebFluxTest(PlayerController.class)
public class PlayerControllerTest {

    @MockBean
    private PlayerService playerService;

    @Autowired
    public WebTestClient webClient;

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