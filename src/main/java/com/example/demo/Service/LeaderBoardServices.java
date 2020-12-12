package com.example.demo.Service;


import com.example.demo.Registration.LeaderBoardRegistration;
import com.example.demo.dao.LeaderBoardRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.LeaderBoardUser;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderBoardServices implements LeaderBoardRegistration<String,LeaderBoardUser> {

    LeaderBoardServices services;

    @Autowired
    LeaderBoardRepository leaderBoardRepository;
/*
@GetMapping
public List<User> getTopTenUsers() throws Exception {
    if (usersRepository.findAll().isEmpty()) {
        throw new Exception("Collection is Empty");
    } else {
        List<User> usersList = usersRepository.findAll();
        List<User> shortedUsers = usersList.stream().sorted(Comparator.comparing(User::getScore).reversed()).collect(Collectors.toList());

        List<User> shortedTenUsers = new ArrayList<>();
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

 */

    public void  LeaderBoard(LeaderBoardServices services)
    {
        this.services = services;
    }


    @Override
    public LeaderBoardUser addLeaderBoardUser(LeaderBoardUser leaderBoardUser) throws Exception {
        if (leaderBoardRepository.existsById(leaderBoardUser.getLeaderBoardName())) {
            throw new Exception("name dosen't exist" + leaderBoardUser.getId());
        }else {
            return leaderBoardRepository.save(leaderBoardUser);
        }
    }

    @Override
    public List<LeaderBoardUser> getLeaderBoard() throws Exception {
        if (leaderBoardRepository.findAll().isEmpty()) {
            throw new Exception("Collection is Empty");
        } else {
           return leaderBoardRepository.findAll();
        }


    }

    @Override
    public LeaderBoardUser updateLeaderBoardUser(String leaderBoardName, LeaderBoardUser leaderBoardUser) throws Exception {
        if (leaderBoardRepository.existsById(leaderBoardUser.getLeaderBoardName())) {
            throw new Exception("Name dosen't exist" + leaderBoardUser.getId());
        }else {
            return leaderBoardRepository.save(leaderBoardUser);
        }
    }


}




