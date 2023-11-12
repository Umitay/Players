/*


package com.intuit.players.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;


@WebFluxTest(controllers = PlayerController.class)
class PlayerControllerTest {

    @MockBean
    private PlayerController playerController;

    @Autowired
    private WebTestClient webClient;


    @Test
    void getAllPlayers() {
        webClient.get().uri("/api/players")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getPlayerById() {

        webClient.get().uri("/{id}", 100)
                .exchange()
                .expectStatus().isOk();

    }
}

 */