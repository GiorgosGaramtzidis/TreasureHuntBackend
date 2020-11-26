package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Objects;
@AllArgsConstructor
@Getter
@Setter

@Document(collection = "Questions")
public class QuestionsNew
{
    @org.springframework.data.annotation.Id
    private @NonNull String id;

    private @NonNull String question;
    private @NonNull String answer;
    private @NonNull int score;



}
