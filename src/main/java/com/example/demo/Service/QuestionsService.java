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


    @Override
    public Boolean addQuestion(Question question) throws Exception {
        if (questionsRepository.existsByQuestion(question.getQuestion()))
            throw new Exception("Same question exists");
        String id = generateId();
        while(questionsRepository.existsById(id))
        {
            id =generateId();
        }
        question.setId(id);
        questionsRepository.save(question);
        return true;
    }

    public Question getNewQuestion(List<Question> questionList) throws Exception {
        Question randomQuestion  = getRandomQuestion();
        while(randomQuestion.ifQuestionExist(questionList)){
            randomQuestion = getRandomQuestion();
        }
        return randomQuestion;
    }

    public String generateId()
    {
        String pattern = "1234567890" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(5);
        Random random = new Random();
        int randomNum = random.nextInt((10));
        for (int i=0; i< randomNum ; i++)
        {
            int index = (int)(pattern.length() * Math.random());
            sb.append(pattern.charAt(index));
        }
        return sb.toString();
    }

}
