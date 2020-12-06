package com.example.demo.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerCheckControllerTest {
    @Autowired
    AnswerCheckController answerCheckController;

    @Test
    public void answerCheckIfLocationTitleExistsAndAnswerIsCorrect() throws Exception{
        String usersAnswer = "Κουτάλι";
        String locationTitle = "Start";
        Integer i=1;
        assertEquals(new ResponseEntity<>(1, HttpStatus.OK),answerCheckController.AnswerCheck(usersAnswer,locationTitle));
    }

    @Test
    public void answerCheckIfLocationTitleExistsAndAnswerIsNotCorrect() throws Exception{
        String usersAnswer = "dfsgdah";
        String locationTitle = "Start";
        Integer i=0;
        assertEquals(new ResponseEntity<>(0, HttpStatus.OK),answerCheckController.AnswerCheck(usersAnswer,locationTitle));
    }
    @Test(expected = Exception.class)
    public void answerCheckIfLocationTitleDoesntExistsAndAnswerIsNotCorrectOrCorrect() throws Exception{
        String usersAnswer = "dfsgdah";
        String locationTitle = "GFDSGFD";
        Integer i=0;
        assertEquals(new ResponseEntity<>(0, HttpStatus.OK),answerCheckController.AnswerCheck(usersAnswer,locationTitle));
    }

}
