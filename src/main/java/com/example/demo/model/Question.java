package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter

@Document(collection = "Questions")
public class Question {

    @Id
    private String id;
    private String question;
    private String answer;
    private int points;

    public boolean ifQuestionExist(List<Question> questionList){

        Question question = new Question(this.id,this.question,this.answer,this.points);

        for (int i=0;i<questionList.size();i++){
            System.out.println(questionList.get(i).toString().equals(question.toString()));
            if (questionList.get(i).toString().equals(question.toString())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + points +
                '}';
    }
}

