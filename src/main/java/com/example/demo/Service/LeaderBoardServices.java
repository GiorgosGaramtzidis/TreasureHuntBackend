package com.example.demo.Service;


import com.example.demo.Registration.LeaderBoardRegistration;
import com.example.demo.dao.LeaderBoardRepository;
import com.example.demo.model.LeaderBoardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderBoardServices implements LeaderBoardRegistration<String,LeaderBoardUser> {



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




    @Override
    public LeaderBoardUser addLeaderBoardUser(LeaderBoardUser leaderBoardUser) throws Exception {
        if (leaderBoardRepository.existsById(leaderBoardUser.getLeaderBoardName())) {
            throw new Exception("name exist" + leaderBoardUser.getId());
        }else {
            return leaderBoardRepository.save(leaderBoardUser);
        }
    }

    @Override
    public List<LeaderBoardUser> getLeaderBoard() throws Exception {
        if (leaderBoardRepository.findAll().isEmpty()) {
            throw new Exception("Collection is Empty");
        } else {
           // List<LeaderBoardUser> leaderBoardUsers = getLeaderBoard();
           List<LeaderBoardUser> leaderBoardUsers = leaderBoardRepository.findAll().stream().sorted(Comparator.comparing(LeaderBoardUser::getScore).reversed()).collect(Collectors.toList());

            return  leaderBoardUsers;
           //return leaderBoardRepository.findAll();
        }
    }


    @Override
    public Integer updateLeaderBoard(String userName,int score) throws Exception {

            List<LeaderBoardUser> leaderBoardUsers = leaderBoardRepository.findAll().stream()
                    .filter(leaderBoardUser -> leaderBoardUser.getLeaderBoardName()
                            .equals(userName))
                    .collect(Collectors.toList());
            if(!leaderBoardUsers.isEmpty()){
                leaderBoardUsers.get(0).setScore(leaderBoardUsers.get(0).getScore()+score);
                leaderBoardRepository.save(leaderBoardUsers.get(0));
                return leaderBoardUsers.get(0).getScore();

        }
        else {
            throw new Exception("Wrong Name");
        }


    }






}




