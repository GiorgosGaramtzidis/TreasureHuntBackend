package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "MultipleChoiceQuestions")
public class MultipleChoiceQuestions {

    @Id
    public int questionId;
    public String question;
    public String wrongAnswer1;
    public String wrongAnswer2;
    public String correctAnswer;
    public int points;

    public MultipleChoiceQuestions(){
        super();
    }

    public MultipleChoiceQuestions(int questionId, String question, String wrongAnswer1, String wrongAnswer2, String correctAnswer, int points) {
        this.questionId = questionId;
        this.question = question;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Multiple Choice Questions{" +
                "id=" + questionId +
                ", question='" + question + '\'' +
                ", wrongAnswer1='" + wrongAnswer1 + '\'' +
                ", wrongAnswer2='" + wrongAnswer2 + '\'' +
                ", answer='" + correctAnswer + '\'' +
                ", points=" + points +
                '}';


    }
}
