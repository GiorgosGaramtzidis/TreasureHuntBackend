package com.example.demo.dao;

import com.example.demo.model.Locations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Locations,Integer> {


}
