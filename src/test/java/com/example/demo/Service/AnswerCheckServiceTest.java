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
    public void CheckingTheAnswerIfItsTheCorrectOneWhileQuestionExists() throws Exception {
        String usersAnswer = "Κουτάλι";
        String question = "Όλους ταΐζει και δεν τρώει. Τι είναι;";
        assertEquals(true,answerCheckService.AnswerCheck(usersAnswer,question));
    }

    @Test
    public void CheckingTheAnswerIfItsWrongWhileQuestionExists() throws Exception {
        String usersAnswer = "foo";
        String question = "Όλους ταΐζει και δεν τρώει. Τι είναι;";
        assertEquals(false,answerCheckService.AnswerCheck(usersAnswer,question));
    }
    @Test(expected = Exception.class)
    public void CheckingTheAnswerWhileQuestionDoesntExistsAndAnswerIsWrong() throws Exception{
        String usersAnswer = "dfsgdah";
        String question = "GFDSGFD";
        assertEquals(false,answerCheckService.AnswerCheck(usersAnswer,question));
    }
    @Test(expected = Exception.class)
    public void CheckingTheAnswerWhileQuestionDoesntExistsAndAnswerIsCorrect() throws Exception{
        String usersAnswer = "Κουτάλι";
        String question = "GFDSGFD";
        assertEquals(false,answerCheckService.AnswerCheck(usersAnswer,question));
    }
}