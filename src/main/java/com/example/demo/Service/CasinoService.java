package com.example.demo.Service;

import com.example.demo.Registration.CasinoRegistration;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CasinoService implements CasinoRegistration<String> {
    private Boolean hasWonRisk;

    @Autowired
    UsersRepository usersRepository;

    public int rollDice() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(1) + 1;
    }

    @Override
    public Boolean casinoRisk(String userName) throws Exception {
        if(usersRepository.existsByUserName(userName))
        {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());


            if(rollDice()==0){
                user.get(0).setScore(user.get(0).getScore()-user.get(0).getScore());
                usersRepository.save(user.get(0));
                user.get(0).getScore();
                hasWonRisk = false;
                return false;

            } else{
                user.get(0).setScore(user.get(0).getScore()+user.get(0).getScore());
                usersRepository.save(user.get(0));
                user.get(0).getScore();
                hasWonRisk = true;
                System.out.println("------");
                return true;
            }
        }else {
            throw new Exception("Wrong id");
        }
    }
}
