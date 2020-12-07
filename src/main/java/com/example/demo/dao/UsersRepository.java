package com.example.demo.dao;
import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User,String>
{
    Boolean existsByUserName(String userName);

}
