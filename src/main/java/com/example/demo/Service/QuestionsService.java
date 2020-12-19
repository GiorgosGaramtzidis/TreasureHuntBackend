package com.example.demo.Service;


import com.example.demo.Registration.QuestionsRegistration;
import com.example.demo.dao.QuestionsRepository;
import com.example.demo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionsService implements QuestionsRegistration<Question> {

    @Autowired
    QuestionsRepository questionsRepository;

    @Override
    public Question getRandomQuestion() throws Exception{
        if (questionsRepository.findAll()==null){
            throw new Exception("Empty Location");
        }
        else
        {
            List<Question> questionList = questionsRepository.findAll().stream().collect(Collectors.toList());
            Random random = new Random();
            int randomNum = random.ints(0,questionList.size()).findFirst().getAsInt();
            return questionList.get(randomNum);
        }
    }

    public Question getNewQuestion(List<Question> questionList) throws Exception {
        Question randomQuestion  = getRandomQuestion();
        while(randomQuestion.ifQuestionExist(questionList)){
            randomQuestion = getRandomQuestion();
        }
        return randomQuestion;
    }
}
