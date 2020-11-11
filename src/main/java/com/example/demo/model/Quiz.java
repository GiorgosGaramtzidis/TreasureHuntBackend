package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document(collection = "Quiz")
public class Quiz
{
    @Id
    public int quizId;
    public String quiz;
    public String answer;
    public int points;
    public ArrayList<String> characters;


    public Quiz(int quizId, String quiz, String answer, int points, ArrayList<String> characters) {
        this.quizId = quizId;
        this.quiz = quiz;
        this.answer = answer;
        this.points = points;
        this.characters = characters;
    }
}
