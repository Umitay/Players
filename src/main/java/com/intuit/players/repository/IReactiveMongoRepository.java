package com.intuit.players.repository;

import com.intuit.players.model.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IReactiveMongoRepository extends ReactiveMongoRepository<Player, String> {

}
