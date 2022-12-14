package com.example.demo.dao;


import com.example.demo.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends MongoRepository<Question,String> {

    Boolean existsByQuestion(String question);
}
