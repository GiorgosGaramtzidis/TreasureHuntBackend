package com.example.demo.Service;


import com.example.demo.Registration.LoseConditionRegistration;


import com.example.demo.dao.UsersRepository;
import com.example.demo.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoseConditionService implements LoseConditionRegistration<Users> {

    @Autowired
    UsersRepository usersRepository;
    @Override
    public Integer loseCondition(Users user) throws Exception {
        if(usersRepository.existsById(user.getId())){
        if(user.getUserLives()<=1){
            throw new Exception("Out of lives");
        }
        else{
            user.setUserLives(user.getUserLives()-1);
            usersRepository.save(user);
            return user.getUserLives();
        }};
        return 1;
    }
}
