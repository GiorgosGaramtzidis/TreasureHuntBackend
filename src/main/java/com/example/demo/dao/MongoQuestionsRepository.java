package com.example.demo.dao;

import com.example.demo.model.QuestionsNew;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface MongoQuestionsRepository extends MongoRepository<QuestionsNew , String> {
}
