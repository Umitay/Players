package com.intuit.players.controller;

import com.intuit.players.model.Player;
import com.intuit.players.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/api/players")
public class PlayerController{

    @Autowired
    public   PlayerService playerService;

    @GetMapping
    public Flux<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{playerId}")
    public Mono<Player> getPlayerById(@PathVariable(required = true) String playerId) {
        return playerService.getPlayerById(playerId);
    }
}
