package com.example.demo.Service;

import com.example.demo.dao.QuestionsRepository;
import com.example.demo.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerCheckServiceTest {

    @Autowired
    AnswerCheckService answerCheckService;


    @Test
    public void CheckingTheAnswerIfItsTheCorrectOneWhileLocationTitleExists() throws Exception {
        String usersAnswer = "Κουτάλι";
        String locationTitle = "Όλους ταΐζει και δεν τρώει. Τι είναι;";
        assertEquals(true,answerCheckService.AnswerCheck(usersAnswer,locationTitle));
    }

    @Test
    public void CheckingTheAnswerIfItsWrongWhileLocationTitleExists() throws Exception {
        String usersAnswer = "wrong";
        String locationTitle = "Όλους ταΐζει και δεν τρώει. Τι είναι;";
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