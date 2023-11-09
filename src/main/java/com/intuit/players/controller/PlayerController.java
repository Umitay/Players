package com.intuit.players.controller;

import com.intuit.players.model.Address;
import com.intuit.players.model.CustomDate;
import com.intuit.players.model.FIO;
import com.intuit.players.model.Player;
import com.intuit.players.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
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
    public Mono<Player> getPlayerById(@PathVariable(required = true) String playerId) {

        return  playerService.getPlayerById(playerId);
    }
}
