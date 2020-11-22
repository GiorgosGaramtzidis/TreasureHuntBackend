package com.example.demo.dao;
import com.example.demo.model.UsersNew;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepositoryNew extends MongoRepository<UsersNew,String> {
}
