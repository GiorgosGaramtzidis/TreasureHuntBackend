package com.example.demo.Registration;

import com.example.demo.model.MultipleChoiceQuestionsNew;

import java.util.List;

public interface MultipleChoiceQuestionRegistration<ID,MCQ>{
    MCQ registerNewMCQ(MCQ mcq) throws Exception;

    List<MultipleChoiceQuestionsNew> getAllMCQ() throws Exception;
}