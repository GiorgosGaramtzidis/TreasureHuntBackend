package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@Getter
@Setter


@Document(collection = "MultipleChoiceQuestions")
public class MultipleChoiceQuestionsNew {

    @Id
    public ObjectId id;
    public String question;
    public String wrongAnswer1;
    public String wrongAnswer2;
    public String correctAnswer;
    public int points;
}
