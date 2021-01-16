package com.example.demo.Registration;

import java.util.HashMap;
import java.util.List;
import com.example.demo.model.GameLocation;
import com.example.demo.model.GameState;

import java.util.Optional;

public interface TreasureHuntGameRegistration<LOCATIONS> {

    Boolean createGame(String id,String gameName,String gameLocation) throws Exception;
    Optional getGame(String id) throws Exception;
    Boolean addUser(String userName,String id) throws Exception;
    List getAvailableGames() throws Exception;

    Boolean addLocation(GameLocation gameLocation, String id)throws Exception;

    void updateGameState(String id,String gameState)throws Exception;
}
