package com.example.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Questions")
public class Questions {

    @Id
    public int questionId;
    public String question;
    public String answer;
    public int points;

    public Questions(){
        super();
    }

    public Questions(int questionId, String question, String answer, int points) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + questionId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + points +
                '}';
    }
}

