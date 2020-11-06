package com.example.demo.dao;

import com.example.demo.model.Questions;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface QuestionsRepository extends MongoRepository<Questions, Integer>{

}
