package com.example.demo.Service;

import com.example.demo.dao.LeaderBoardRepository;
import com.example.demo.dao.TreasureHuntGameRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TreasureHuntGameServiceTest {

    @MockBean
    TreasureHuntGameRepository treasureHuntGameRepository;
    @MockBean
    UsersRepository usersRepository;

    @Autowired
    TreasureHuntGameService treasureHuntGameService;

    @Autowired
    UsersService usersService;





    @Test
    public void createGame() {
   assertEquals(treasureHuntGameService.createGame("123","Serres","Makedonia"),true);
    }

    @Test
    public void IfGameIdExist() throws Exception{
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        treasureHuntGame.setId("1");

        when(treasureHuntGameRepository.existsById("1")).thenReturn(true);
        treasureHuntGameService.getGame("1");
    }


    @Test(expected = Exception.class)
    public void IfGameIdDoesNotExist() throws Exception{
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        treasureHuntGame.setId("1");

        when(treasureHuntGameRepository.existsById("2")).thenReturn(false);
        treasureHuntGameService.getGame("2");
    }


    @Test
    public void IfGameExist() throws Exception{

        User user = new User("1","Nikos123",0,"Nikos123@",5, Status.Connected, UserState.PLAYING,UserRole.Player);
        List<TreasureHuntGame> treasureHuntGames = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.add(user);
        List<LocationsNew> locations = new ArrayList<>();
        List<UserPosition> userpositions = new ArrayList<>();
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame("1234","serres","makedonia",users,locations,userpositions,GameState.DidNotStart,user);
        treasureHuntGames.add(treasureHuntGame);
        when(treasureHuntGameRepository.findAll()).thenReturn(treasureHuntGames);
        when(usersRepository.findUserByUserName("Nikos123")).thenReturn(user);
        treasureHuntGameService.addUser("NIKOS","1234");
    }


    @Test(expected = Exception.class)
    public void IfGameDoesNotExist() throws Exception{
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        treasureHuntGame.setId("2");
        when(treasureHuntGameRepository.existsById("1")).thenReturn(false);
        treasureHuntGameService.addUser("Giorgos","1");


    }

}