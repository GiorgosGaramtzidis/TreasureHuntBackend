package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class UsersNew {
    @Id
    private String id;
    private String name;
    private int score;
    private String password;
}
