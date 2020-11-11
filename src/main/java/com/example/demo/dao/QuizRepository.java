package com.example.demo.dao;

import com.example.demo.model.Questions;
import com.example.demo.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends MongoRepository<Quiz,Integer>
{
    @Query(value = "{ 'points' :  ?0 }")
    List<Quiz> getQuestionWithPoints(int points);
}
