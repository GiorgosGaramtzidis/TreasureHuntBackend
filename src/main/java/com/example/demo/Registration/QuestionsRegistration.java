package com.example.demo.Registration;

public interface QuestionsRegistration<QUESTION> {

    QUESTION getRandomQuestion() throws Exception;

    Boolean addQuestion(QUESTION question)throws Exception;
}
