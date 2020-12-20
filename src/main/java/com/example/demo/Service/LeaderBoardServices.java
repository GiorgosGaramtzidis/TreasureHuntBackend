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

           List<LeaderBoardUser> leaderBoardUsers = leaderBoardRepository.findAll().stream().sorted(Comparator.comparing(LeaderBoardUser::getScore).reversed()).collect(Collectors.toList());

            return  leaderBoardUsers;

        }
    }


@Override
public Integer updateLeaderBoard(String leaderBoardName,int score) throws Exception {
    if(leaderBoardRepository.existsByLeaderBoardName(leaderBoardName)){
    List<LeaderBoardUser> leaderBoardUsers = leaderBoardRepository.findAll().stream()
            .filter(leaderBoardUser -> leaderBoardUser.getLeaderBoardName()
                    .equals(leaderBoardName))
            .collect(Collectors.toList());

    leaderBoardUsers.get(0).setScore(leaderBoardUsers.get(0).getScore() + score);
    leaderBoardRepository.save(leaderBoardUsers.get(0));
    return leaderBoardUsers.get(0).getScore();

}
    else {
        throw new Exception("Wrong Name");
    }

}

}




