package com.example.demo.dao;

import com.example.demo.model.LocationsNew;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsRepositoryNew  extends MongoRepository<LocationsNew, ObjectId> {
}
