package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "DemoWatchTower")
public class WatchTower {

    private String userName;
    private String locationTitle;

}
