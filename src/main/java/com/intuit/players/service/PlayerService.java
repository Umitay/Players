package com.intuit.players.service;

import com.intuit.players.model.Player;
import com.intuit.players.repository.IReactiveMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Slf4j
//@RequiredArgsConstructor
@Transactional
public class PlayerService {
    private final IReactiveMongoRepository playerRepository;

    @Autowired
    public PlayerService(IReactiveMongoRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Mono<Player> getPlayerById(String playerId) {
         return playerRepository.findById(playerId);
    }

    public Flux<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
