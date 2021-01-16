package com.example.demo.dao;

import com.example.demo.model.TreasureHuntGame;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TreasureHuntGameRepository extends MongoRepository<TreasureHuntGame,String> {
TreasureHuntGame findTreasureHuntGameById(String id);
}
