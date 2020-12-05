package com.example.demo.dao;
import com.example.demo.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<Users,String>
{
    Boolean existsByUserName(String userName);
}
