package com.example.demo.dao;

import com.example.demo.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends MongoRepository<Users, Integer> {

    @Query(value = "{ 'score' :  ?0 }")
    List<Users> getUsersWithScore(int score);
}
