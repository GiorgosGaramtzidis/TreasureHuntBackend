package com.example.demo.dao;

import com.example.demo.model.MultipleChoiceQuestionsNew;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MultipleChoiceQuestionsRepositoryNew extends MongoRepository<MultipleChoiceQuestionsNew, ObjectId> {
}
