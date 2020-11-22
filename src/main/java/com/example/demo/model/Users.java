package com.example.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class Users {

    @Id
    public int userId;
    public String name;
    public int score;
    public String password;

    public Users(){
        super();
    }

    public Users(int userId, String name, int score,String password) {
        this.userId = userId;
        this.name = name;
        this.score = score;
        this.password= password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", password='" + password + '\'' +
                '}';
    }
}
