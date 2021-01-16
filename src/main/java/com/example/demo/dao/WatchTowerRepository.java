package com.example.demo.dao;

import com.example.demo.model.WatchTower;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WatchTowerRepository extends MongoRepository<WatchTower,String> {

    Boolean existsByUserName(String userName);

    void deleteByUserName(String userName);
}
