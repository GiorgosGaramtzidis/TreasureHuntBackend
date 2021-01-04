package com.example.demo.Registration;

import com.example.demo.model.WatchTower;

import java.util.List;

public interface WatchTowerRegistration<STRING> {

    List<WatchTower> getWatchTowerList() throws Exception;

    void deleteFromWatchTower(STRING userName) throws Exception;

    WatchTower addInWatchTower(STRING userName,STRING locationTitle) throws Exception;

    void deleteAllFromWatchTower() throws Exception;
}
