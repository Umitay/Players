package com.intuit.players.component;

import com.intuit.players.config.TestConfig;
import com.intuit.players.controller.PlayerController;
import com.intuit.players.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;
@SpringBootTest
public class PlayerComponentTest {

@Autowired
public PlayerController playerController;
    public WebTestClient webClient;

    @BeforeEach
    void before(){
        webClient = WebTestClient.bindToController(playerController).build();
    }

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