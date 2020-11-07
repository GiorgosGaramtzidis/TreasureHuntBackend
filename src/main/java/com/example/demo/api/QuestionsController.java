package com.example.demo.api;


import com.example.demo.dao.QuestionsRepository;
import com.example.demo.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/Questions")
@RestController
public class QuestionsController {

    @Autowired
    public QuestionsRepository questionsRepository;

    @GetMapping(value = "/find")
    public List<Questions> getAllQuestions(){

        return questionsRepository.findAll();
    }

    @GetMapping(value = "/find/{id}")
    public Optional<Questions> getQuestionById(@PathVariable int id){

        return questionsRepository.findById(id);
    }

    @GetMapping(value = "/find/points/{points}")
    public List<Questions> getQuestionWithPoints(@PathVariable int points) {
        return questionsRepository.getQuestionWithPoints(points);
    }

    @PostMapping(value = "/addQuestion")
    public String saveQuestion(@RequestBody Questions questions){
        questionsRepository.save(questions);
        return "Added question";
    }

    @DeleteMapping(value = "/delete")
    public String deleteAllQuestion(){
        questionsRepository.deleteAll();
        return "Delete All Questions";
    }
}

