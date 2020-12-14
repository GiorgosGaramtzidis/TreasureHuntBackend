package com.example.demo.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerCheckServiceTest {

    @Autowired
    AnswerCheckService answerCheckService;

    @Test
    public void CheckingTheAnswerIfItsTheCorrectOneWhileLocationTitleExists() throws Exception {
        String usersAnswer = "Κουτάλι";
        String locationTitle = "Start";
        assertEquals(true,answerCheckService.AnswerCheck(usersAnswer,locationTitle));
    }

    @Test
    public void CheckingTheAnswerIfItsWrongWhileLocationTitleExists() throws Exception {
        String usersAnswer = "Κουτfsaάλι";
        String locationTitle = "Start";
        assertEquals(false,answerCheckService.AnswerCheck(usersAnswer,locationTitle));
    }
    @Test(expected = Exception.class)
    public void CheckingTheAnswerWhileLocationTitleDoesntExistsAndAnswerIsWrong() throws Exception{
        String usersAnswer = "dfsgdah";
        String locationTitle = "GFDSGFD";
        assertEquals(false,answerCheckService.AnswerCheck(usersAnswer,locationTitle));
    }
    @Test(expected = Exception.class)
    public void CheckingTheAnswerWhileLocationTitleDoesntExistsAndAnswerIsCorrect() throws Exception{
        String usersAnswer = "Κουτάλι";
        String locationTitle = "GFDSGFD";
        assertEquals(false,answerCheckService.AnswerCheck(usersAnswer,locationTitle));
    }
}