package com.example.demo.api;


import com.example.demo.dao.QuestionsRepository;
import com.example.demo.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/Questions")
@RestController
public class QuestionsController {

    @Autowired
    public QuestionsRepository questionsRepository;

    @GetMapping(value = "/all")
    public List<Questions> getAllQuestions(){

        return questionsRepository.findAll();
    }

    @PostMapping(value = "/addQuestion")
    public String saveQuestion(@RequestBody Questions questions){
        questionsRepository.save(questions);
        return "Added question";
    }

    public List<Questions> getAllQuestions1(){

        return questionsRepository.findAll();
    }
}

