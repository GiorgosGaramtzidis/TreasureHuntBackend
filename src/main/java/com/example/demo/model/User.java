package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String userName;
    private int score;
    private String password;
    private int userLives;
    private Status status;
    private UserState userState;
    private UserRole userRole ;

    //Player Creation
    public User(String userName , String passWord)
    {
        this.userName = userName;
        this.password = passWord;
        this.score = 0;
        this.userLives = 5;
        this.status = Status.Away;
        this.userState = UserState.PLAYING;
        this.userRole = UserRole.Player;
    }

}
