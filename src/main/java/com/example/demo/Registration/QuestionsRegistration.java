package com.example.demo.Registration;

import com.example.demo.model.QuestionsNew;
import com.example.demo.dao.MongoQuestionsRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionsRegistration<ID,QUESTIONS>
{
    Boolean addQuestions(QUESTIONS questions) throws Exception;



    Optional<QuestionsNew> getQuestions(ID questionsId) throws Exception;

    QUESTIONS updateQuestions(ID questionsId,QUESTIONS questions) throws Exception;


    void deleteQuestion(ID questionsid) throws Exception;
}
