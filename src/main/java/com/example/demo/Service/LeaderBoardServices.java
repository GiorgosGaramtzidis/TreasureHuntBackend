package com.example.demo.Service;


import com.example.demo.dao.UsersRepository;
import com.example.demo.model.LeaderBoard;
import com.example.demo.model.Locations;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderBoardServices {

    LeaderBoardServices services;

    @Autowired
    UsersRepository usersRepository;

@GetMapping
public List<Users> getTopTenUsers() throws Exception {
    if (usersRepository.findAll().isEmpty()) {
        throw new Exception("Collection is Empty");
    } else {
        List<Users> usersList = usersRepository.findAll();
        List<Users> shortedUsers = usersList.stream().sorted(Comparator.comparing(Users::getScore).reversed()).collect(Collectors.toList());

        List<Users> shortedTenUsers = new ArrayList<>();
        if (shortedUsers.size() < 10) {
            return shortedUsers;
        } else {

            for (int i = 0; i < 10; i++) {
                shortedTenUsers.add(i, shortedUsers.get(i));
            }
            return shortedTenUsers;
        }
    }

    }

    public void  LeaderBoard(LeaderBoardServices services)
    {
        this.services = services;
    }


  @GetMapping
    public List<Users> getLeaderBoard() throws Exception {
        if (usersRepository.findAll().isEmpty()) {
            throw new Exception("Collection is Empty");
        } else {
            List<Users> usersList = usersRepository.findAll();
            List<Users> shortedUsers = usersList.stream().sorted(Comparator.comparing(Users::getScore).reversed()).collect(Collectors.toList());
            return shortedUsers;
        }


    }


}




