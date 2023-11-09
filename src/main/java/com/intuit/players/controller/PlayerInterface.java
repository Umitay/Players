package com.intuit.players.controller;

import com.intuit.players.model.Player;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerInterface {
    Flux<Player> getAllPlayers();
    Mono<Player> getPlayerById(@PathVariable(required = true) String playerId);
}
