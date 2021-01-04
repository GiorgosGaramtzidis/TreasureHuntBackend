package com.example.demo.Registration;

import com.example.demo.model.WatchTower;

import java.util.List;

public interface WatchTowerRegistration<STRING> {

    List<WatchTower> getWatchTowerList() throws Exception;

    WatchTower updateWatchTower(STRING userName,STRING locationTitle) throws Exception;

    WatchTower addWatchTower(STRING userName,STRING locationTitle) throws Exception;
}
