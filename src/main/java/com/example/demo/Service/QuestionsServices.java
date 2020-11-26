package com.example.demo.Service;


import com.example.demo.model.QuestionsNew;
import com.example.demo.Registration.QuestionsRegistration;
import com.example.demo.dao.MongoQuestionsRepository;
import com.example.demo.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsServices implements QuestionsRegistration<String, QuestionsNew>
{

    @Autowired
private MongoQuestionsRepository mongoQuestionsRepository;

    @Override
    public Boolean addQuestions(QuestionsNew questions) throws Exception {

        if (mongoQuestionsRepository.existsById(questions.getId())) {
            return false;
        }
        mongoQuestionsRepository.save(questions);
        return true;
    }


    @Override
    public Optional<QuestionsNew> getQuestions(String questionsId) throws Exception {
        if (mongoQuestionsRepository.existsById(questionsId)==false){
            throw new Exception("TO ID DEN UPARXEI");
        } else {
            return mongoQuestionsRepository.findById(questionsId);
        }
    }



    public List<QuestionsNew> getAllQuestions() throws Exception{
        if (mongoQuestionsRepository.findAll().isEmpty()){
            throw new Exception("Locations collection is empty");
        } else {
            return mongoQuestionsRepository.findAll();
        }
    }




    @Override
    public QuestionsNew updateQuestions(String questionId,QuestionsNew questionsNew) throws Exception {
        if (mongoQuestionsRepository.existsById(questionId)==false){
            throw new Exception("TO ID DEN UPARXEI");
        } else {
            return mongoQuestionsRepository.save(questionsNew);
        }
    }

    @Override
    public void deleteQuestion(String questionsid) throws Exception {
        if (questionsid == null){
            throw new Exception("question id is null");
        }else
            mongoQuestionsRepository.deleteById(questionsid);

    }

}
