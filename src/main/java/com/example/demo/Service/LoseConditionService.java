package com.example.demo.Service;


import com.example.demo.Registration.LoseConditionRegistration;


import com.example.demo.dao.UsersRepository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoseConditionService implements LoseConditionRegistration<User> {
    private Boolean hasLost;
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Boolean loseCondition(String userName) throws Exception {
        if(usersRepository.existsByUserName(userName))
        {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());

                if(user.get(0).getUserLives()<=1){
                    hasLost = true;
                    System.out.println("+++++");
                    return true;

                } else{
                     user.get(0).setUserLives(user.get(0).getUserLives()-1);
                     usersRepository.save(user.get(0));
                     user.get(0).getUserLives();
                     hasLost = false;
                    System.out.println("------");
                     return false;
                }
        }else {
            throw new Exception("Wrong id");
        }
    }
}
