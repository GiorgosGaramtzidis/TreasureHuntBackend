package com.example.demo.api;

import com.example.demo.Service.AnswerCheckService;
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

        return new ResponseEntity<>(answerCheckService.AnswerCheck(usersAnswer,locationTitle), HttpStatus.OK);
    }
}
