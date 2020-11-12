package com.example.demo.dao;

import com.example.demo.model.MultipleChoiceQuestions;
import com.example.demo.model.Questions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultipleChoiceQuestionsRepository extends MongoRepository<MultipleChoiceQuestions, Integer> {

    @Query(value = "{ 'points' :  ?0 }")
    List<MultipleChoiceQuestions> getMultipleChoiceQuestionWithPoints(int points);

}
