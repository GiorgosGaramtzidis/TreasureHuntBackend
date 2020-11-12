package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Locations")
public class Locations  {

    @Id
    public int Id;
    public double v;
    public double v1;
    public String title;
    public String color;

    public Locations(){
        super();
    }

    public Locations(int id, double v, double v1, String title, String color) {
        this.Id = id;
        this.v = v;
        this.v1 = v1;
        this.title = title;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "Id=" + Id +
                ", v=" + v +
                ", v1=" + v1 +
                ", title='" + title + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}