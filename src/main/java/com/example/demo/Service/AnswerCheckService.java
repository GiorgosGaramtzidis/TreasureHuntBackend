package com.example.demo.Service;

import com.example.demo.Registration.AnswerCheck;
import com.example.demo.api.AnswerCheckController;
import com.example.demo.dao.LocationsRepositoryNew;

import com.example.demo.dao.QuestionsRepository;
import com.example.demo.model.LocationsNew;
import com.example.demo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerCheckService implements AnswerCheck<String> {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public Boolean AnswerCheck(String usersAnswer,String question) throws Exception {

        if(questionsRepository.existsByQuestion(question)) {
            List<Question> questionList = questionsRepository.findAll().stream().filter(question1 -> question1.getQuestion().equals(question))
                    .collect(Collectors.toList());
            if (usersAnswer.equals(questionList.get(0).getAnswer())) {
                return true;
            } else {
                return false;
            }
        }
        throw new Exception("Location Does Not Exist");
    }


}