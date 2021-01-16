package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableGames {
    private String gameLocation;
    private String id;


    public AvailableGames (String gameLocation, String id) {
this.gameLocation = gameLocation;
this.id = id;

    }
}

