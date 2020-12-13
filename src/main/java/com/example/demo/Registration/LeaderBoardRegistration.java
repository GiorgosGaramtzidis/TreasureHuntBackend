package com.example.demo.Registration;

import com.example.demo.model.LeaderBoardUser;
import com.example.demo.model.LocationsNew;

import java.util.List;

public interface LeaderBoardRegistration<NAME,LEADERBOARD> {


    LEADERBOARD addLeaderBoardUser(LEADERBOARD leaderboard) throws Exception;

    List<LeaderBoardUser> getLeaderBoard() throws Exception;

    LEADERBOARD updateLeaderBoardUser(NAME name,LEADERBOARD leaderboard) throws Exception;


}
