package com.example.demo.Service;


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
    public void IfGameExistToAddUser() throws Exception{
        User user = new User("1","Nikos123",0,"Nikos123@",5, Status.Connected, UserState.PLAYING,UserRole.Player);
        List<TreasureHuntGame> treasureHuntGames = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.add(user);
        List<GameLocation> gameLocations = new ArrayList<>();
        List<UserPosition> userpositions = new ArrayList<>();
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame("1234","serres","makedonia",users,gameLocations,userpositions,GameState.DidNotStart,user);
        treasureHuntGames.add(treasureHuntGame);
        when(usersRepository.findUserByUserName("Nikos123")).thenReturn(user);
        when(treasureHuntGameRepository.findTreasureHuntGameById("1234")).thenReturn(treasureHuntGame);
        when(treasureHuntGameRepository.findAll()).thenReturn(treasureHuntGames);
        treasureHuntGameService.addUser("NIKOS","1234");

    }


    @Test(expected = Exception.class)
    public void IfGameDoesNotExistToAddUser() throws Exception{
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        treasureHuntGame.setId("2");
        when(treasureHuntGameRepository.existsById("1")).thenReturn(false);
        treasureHuntGameService.addUser("Giorgos","1");
    }

    @Test
    public void IfGameExistToAddLocation() throws Exception{

       User user = new User("1","Nikos123",0,"Nikos123@",5, Status.Connected, UserState.PLAYING,UserRole.Player);
        List<TreasureHuntGame> treasureHuntGames = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.add(user);
        List<GameLocation> gameLocations = new ArrayList<>();
        List<UserPosition> userpositions = new ArrayList<>();
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame("1234","serres","makedonia",users,gameLocations,userpositions,GameState.DidNotStart,user);
        treasureHuntGames.add(treasureHuntGame);
        GameLocation gameLocation = new GameLocation();
        when(treasureHuntGameRepository.findAll()).thenReturn(treasureHuntGames);
        when(treasureHuntGameRepository.existsById("1234")).thenReturn(true);
        treasureHuntGameService.addLocation(gameLocation,"1234");

    }

    @Test(expected = Exception.class)
    public void IfGameDoesNotExistToAddLocation() throws Exception{
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        treasureHuntGame.setId("2");
        GameLocation gameLocation = new GameLocation();
        when(treasureHuntGameRepository.existsById("1")).thenReturn(false);
        treasureHuntGameService.addLocation(gameLocation,"1");

    }
    @Test
    public void addAPlayerLocationToGame()
    {
        UserPosition userPosition = new UserPosition("George","19.12345","23.12345");
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        treasureHuntGame.setId("1234");
        treasureHuntGame.setUserPositionList(new ArrayList<>());
        List<TreasureHuntGame> list = new ArrayList<>();
        list.add(treasureHuntGame);
        when(treasureHuntGameRepository.findAll()).thenReturn(list);
        treasureHuntGameService.addPlayersLocationToGame(userPosition,"1234");
    }
    @Test
    public void addNewLocationOfAPlayerWhoAlreadyPlaysTheGame()
    {
        UserPosition userPosition = new UserPosition("George","19.12345","23.12345");
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        treasureHuntGame.setId("1234");
        List<UserPosition> positions = new ArrayList<>();
        positions.add(userPosition);
        treasureHuntGame.setUserPositionList(positions);
        List<TreasureHuntGame> list = new ArrayList<>();
        list.add(treasureHuntGame);
        when(treasureHuntGameRepository.findAll()).thenReturn(list);
        treasureHuntGameService.addPlayersLocationToGame(userPosition,"1234");
    }
    @Test
    public void AddAPlayerLocationForTheFirstTime()
    {
        UserPosition userPosition = new UserPosition("George","19.12345","23.12345");
        UserPosition newUserPossition = new UserPosition("Aggelos","19.12345","23.12345");
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        treasureHuntGame.setId("1234");
        List<UserPosition> positions = new ArrayList<>();
        positions.add(userPosition);
        treasureHuntGame.setUserPositionList(positions);
        List<TreasureHuntGame> list = new ArrayList<>();
        list.add(treasureHuntGame);
        when(treasureHuntGameRepository.findAll()).thenReturn(list);
        treasureHuntGameService.addPlayersLocationToGame(newUserPossition,"1234");
    }

}