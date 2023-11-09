package com.intuit.players.repository;

import com.intuit.players.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;


public interface IMongoRepository extends MongoRepository<Player, String> {
}
