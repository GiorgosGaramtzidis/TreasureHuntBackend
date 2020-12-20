package com.example.demo.Service;

import com.example.demo.dao.LeaderBoardRepository;
import com.example.demo.model.LeaderBoardUser;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaderBoardUserServicesTest {
    @MockBean
    LeaderBoardRepository leaderBoardRepository;

    @Autowired
    LeaderBoardServices leaderBoardServices;

    @Test(expected = Exception.class)
    public void IfLeaderBoardUserNameExist () throws Exception
    {
        LeaderBoardUser leaderBoardUser = new LeaderBoardUser();
        leaderBoardUser.setLeaderBoardName("NIKOS");

        when(leaderBoardRepository.existsById("NIKOS")).thenReturn(true);
        leaderBoardServices.addLeaderBoardUser(leaderBoardUser);
    }

    @Test
    public void IfLeaderBoardUserDoesNotExist() throws Exception
    {
        LeaderBoardUser leaderBoardUser = new LeaderBoardUser();
        leaderBoardUser.setLeaderBoardName("NIKOS");

        when(leaderBoardRepository.existsById("NIKOS1")).thenReturn(false);
        leaderBoardServices.addLeaderBoardUser(leaderBoardUser);
    }


    @Test(expected = Exception.class)
    public void IfLeaderBoardUserDoesNotExistForUpDate()throws  Exception{
        LeaderBoardUser leaderBoardUser = new LeaderBoardUser();
        leaderBoardUser.setLeaderBoardName("NIKOS");
        leaderBoardUser.setScore(5);
        List<LeaderBoardUser> leaderBoardUserList = new ArrayList<>();
        leaderBoardUserList.add(leaderBoardUser);

        when(leaderBoardRepository.findAll()).thenReturn(leaderBoardUserList);

        when(leaderBoardRepository.existsByLeaderBoardName("ANNA")).thenReturn(false);
        leaderBoardServices.updateLeaderBoard(leaderBoardUser.getLeaderBoardName(), 500);

    }

    @Test
    public void IfLeaderBoardUserExistForUpDate() throws  Exception{
        LeaderBoardUser leaderBoardUser = new LeaderBoardUser();
        leaderBoardUser.setLeaderBoardName("NIKOS");
        leaderBoardUser.setScore(5);
        List<LeaderBoardUser> leaderBoardUserList = new ArrayList<>();
        leaderBoardUserList.add(leaderBoardUser);

        when(leaderBoardRepository.findAll()).thenReturn(leaderBoardUserList);

        when(leaderBoardRepository.existsByLeaderBoardName("NIKOS")).thenReturn(true);
        leaderBoardServices.updateLeaderBoard(leaderBoardUser.getLeaderBoardName(), 500);

    }

    @Test(expected = Exception.class)
    public void IfLeaderBoardIsEmpty()throws Exception{

        when(leaderBoardServices.getLeaderBoard()).thenReturn(null);
    }

@Test
    public void IfLeaderBoardIsNotEmpty() throws Exception{
        LeaderBoardUser leaderBoardUser = new LeaderBoardUser("1","nikos",2);
        List<LeaderBoardUser> leaderBoardUserList = new ArrayList<>();
         leaderBoardUserList.add(leaderBoardUser);
       when(leaderBoardRepository.findAll()).thenReturn(leaderBoardUserList);
        //leaderBoardRepository.save(leaderBoardUser);
        leaderBoardServices.getLeaderBoard();

}



}