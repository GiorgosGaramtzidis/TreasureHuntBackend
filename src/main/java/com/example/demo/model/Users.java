package com.example.demo.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "UsersNew")
public class Users {

    @Id //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userName;
    private int score;
    private String password;
    private int userLives;
}
