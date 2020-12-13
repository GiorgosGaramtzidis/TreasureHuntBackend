package com.example.demo.dao;

import com.example.demo.model.LeaderBoardUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeaderBoardRepository extends MongoRepository <LeaderBoardUser, String>{
}
