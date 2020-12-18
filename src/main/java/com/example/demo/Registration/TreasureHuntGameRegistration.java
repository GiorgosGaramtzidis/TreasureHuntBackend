package com.example.demo.Registration;

import java.util.Optional;

public interface TreasureHuntGameRegistration<LOCATIONS> {

    Boolean createGame(LOCATIONS LOCATIONS) throws Exception;
    Optional getGame(String id) throws Exception;
    Boolean addUser(String userName,String id) throws Exception;

}
