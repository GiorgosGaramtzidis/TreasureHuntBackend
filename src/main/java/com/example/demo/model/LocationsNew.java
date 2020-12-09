package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NonNull;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter


@Document(collection = "DemoLocations")
public class LocationsNew {

    @Id
    private ObjectId Id;

    @NotNull(message = "coordinate v is mandatory")
    private @NonNull double v;

    @NotNull(message = "coordinate v1 is mandatory")
    private @NonNull double v1;

    private String title;

    private Question questions;

    @NotNull
    private String nextLocation;

    public LocationsNew(String title , String nextLocation) {
        this.title = title;
        this.nextLocation = nextLocation;
    }




    @Override
    public String toString() {
        return "LocationsNew{" +
                "Id=" + Id +
                ", v=" + v +
                ", v1=" + v1 +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                ", nextLocation='" + nextLocation + '\'' +
                '}';
    }
}
