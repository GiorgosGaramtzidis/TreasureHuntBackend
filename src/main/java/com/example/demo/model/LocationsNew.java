package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NonNull;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter


@Document(collection = "Locations")
public class LocationsNew {

    @Id
    private String LocationId;

    @NotNull(message = "coordinate v is mandatory")
    private @NonNull float v;

    @NotNull(message = "coordinate v1 is mandatory")
    private @NonNull float v1;

    private String title;

    private Question questions;
}
