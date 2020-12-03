package com.example.demo.dao;

import com.example.demo.model.LocationsNew;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsRepositoryNew  extends MongoRepository<LocationsNew, ObjectId> {

    @Query("{ 'title' : 'Start' }")
    LocationsNew getStartLocation();


    @Query("{ 'title' : ?0 }")
    LocationsNew getNextLocation(String title);
}
