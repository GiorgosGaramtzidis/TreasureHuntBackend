package com.example.demo.Service;

import com.example.demo.dao.QuestionsRepository;
import com.example.demo.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionsServiceTest {

    @MockBean
    QuestionsRepository questionsRepository;

    @Autowired
    QuestionsService questionsService;

    @Test(expected = Exception.class)
    public void getRandomQuestionWhenCollectionIsEmpty() throws Exception {
        when(questionsRepository.findAll()).thenReturn(null);
        questionsService.getRandomQuestion();
        fail("Collection is Empty");
    }

    @Test
    public void getRandomQuestionWhenCollectionHaveOneQuestion() throws Exception {
        List<Question> questionList = new ArrayList<>();
        Question question = new Question("1","EROTISI","APANTISI",5);


        questionList.add(question);


        when(questionsRepository.findAll()).thenReturn(questionList);

        when(questionsRepository.findAll().stream().collect(Collectors.toList())).thenReturn(questionList);
        Question actualQuestion = questionsService.getRandomQuestion();
        assertEquals(question,actualQuestion);

    }

    @Test
    public void getNewQuestion() {
    }
}