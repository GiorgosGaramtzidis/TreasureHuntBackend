package com.example.demo.Registration;

import java.util.Optional;

public interface TreasureHuntGameRegistration<LOCATIONS> {

    Boolean createGame(String id,String gameName,String gameLocation) throws Exception;
    Optional getGame(String id) throws Exception;
    Boolean addUser(String userName,String id) throws Exception;

}
