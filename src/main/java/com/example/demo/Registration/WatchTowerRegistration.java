package com.example.demo.Registration;

import com.example.demo.model.WatchTower;

import java.util.List;

public interface WatchTowerRegistration<STRING> {

    List<WatchTower> getWatchTowerList() throws Exception;

    WatchTower addInWatchTower(STRING userName,STRING locationTitle) throws Exception;

    Boolean resetWatchTower() throws Exception;
}
