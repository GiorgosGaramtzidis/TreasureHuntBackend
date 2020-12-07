package com.example.demo.api;

import com.example.demo.Service.AnswerCheckService;
import com.example.demo.model.AnswerString;
import com.example.demo.model.LocationsNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/AnswerCheck")
@RestController
public class AnswerCheckController {
    @Autowired
    private AnswerCheckService answerCheckService;


    @GetMapping("/AnswerCheck")
    ResponseEntity AnswerCheck(@RequestParam ("usersAnswer")String usersAnswer ,@RequestParam("locationTitle") String locationTitle) throws Exception{
        //String userAnswer = answerString.getUserAnswer();
     //   String question = answerString.getQuestion();

        return new ResponseEntity<>(answerCheckService.AnswerCheck(usersAnswer,locationTitle), HttpStatus.OK);
    }
}
