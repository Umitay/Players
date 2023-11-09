package com.intuit.players.service;

import com.intuit.players.model.Player;
import com.intuit.players.repository.IMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class PlayerService {
    private final IMongoRepository playerRepository;

    @Autowired
    public PlayerService(IMongoRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Mono<Player> getPlayerById(String playerId) {

        try {
            return Mono.just(playerRepository.findById(playerId).get());
        }catch (Exception e){
            return Mono.empty();
        }

    }

    public Flux<Player> getAllPlayers() {
        try {
            return Flux.fromIterable(playerRepository.findAll());
        }catch (Exception e){
            return Flux.empty();
        }
    }
}
