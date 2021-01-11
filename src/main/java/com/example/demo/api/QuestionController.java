package com.example.demo.api;


import com.example.demo.Service.QuestionsService;
import com.example.demo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/api/Question")
@RestController
public class QuestionController {

    @Autowired
    QuestionsService questionsService;

    @GetMapping(value = "/getRandomQuestion")
    ResponseEntity getRandomQuestion() throws Exception{
        return new ResponseEntity<>(questionsService.getRandomQuestion(), HttpStatus.OK);
    }

    @PostMapping(value = "/getNewQuestion")
    ResponseEntity getNewQuestion(@RequestBody List<Question> questionList) throws Exception{
        return new ResponseEntity<>(questionsService.getNewQuestion(questionList), HttpStatus.OK);
    }

    @PostMapping(value = "/addQuestion")
    ResponseEntity addQuestion(@RequestBody Question question)throws Exception
    {
        return new ResponseEntity(questionsService.addQuestion(question),HttpStatus.OK);
    }
}
