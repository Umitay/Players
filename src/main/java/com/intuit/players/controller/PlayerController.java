package com.intuit.players.controller;

import com.intuit.players.exception.PlayerNotFoundException;
import com.intuit.players.model.Player;
import com.intuit.players.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/api/players")
public class PlayerController implements PlayerInterface{
    private final PlayerService playerService;

    @Autowired
    public PlayerController( PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public Flux<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{playerId}")
    public Mono<ResponseEntity<Player>> getPlayerById(@PathVariable(required = true) String playerId) {
        Mono<Player> player = playerService.getPlayerById(playerId);
        if (player == null) {
            throw new PlayerNotFoundException("Player not found with ID: " + playerId);
        }

        return player.map(ResponseEntity::ok).switchIfEmpty(Mono.error(new PlayerNotFoundException("Not found any players")));
    }
}
