package com.example.demo.Service;

import com.example.demo.Registration.WatchTowerRegistration;
import com.example.demo.dao.WatchTowerRepository;
import com.example.demo.model.WatchTower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchTowerService implements WatchTowerRegistration<String> {
    @Autowired
    private WatchTowerRepository watchTowerRepository;

    @Override
    public List<WatchTower> getWatchTowerList() throws Exception {
        if (watchTowerRepository.findAll().isEmpty())
                throw new Exception("Collection is empty");
       return watchTowerRepository.findAll();
    }

    @Override
    public void deleteFromWatchTower(String userName) throws Exception {
        if(watchTowerRepository.existsByUserName(userName)) {
            watchTowerRepository.deleteByUserName(userName);
            return;
        }
        throw new Exception("Wrong User Name");
    }
    /*@Override
    public WatchTower updateUserFromWatchTower(String userName,String locationTitle) throws Exception {

        if(watchTowerRepository.existsByUserName(userName)) {
            List<WatchTower> watchTowers = watchTowerRepository.findAll().stream()
                    .filter(watchTower1 ->  watchTower1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            watchTowers.get(0).setLocationTitle(locationTitle);
            watchTowerRepository.save(watchTowers.get(0));
        }
        throw new Exception("Wrong User Name");
    }

     */

    @Override
    public WatchTower addInWatchTower(String userName, String locationTitle) throws Exception {
        WatchTower watchTower = new WatchTower();
        watchTower.setUserName(userName);
        watchTower.setLocationTitle(locationTitle);
        if(watchTowerRepository.existsByUserName(userName))
                throw new Exception("User Name Already Exists"+userName);
        return watchTowerRepository.save(watchTower);
    }

    @Override
    public void deleteAllFromWatchTower() throws Exception {
        if(watchTowerRepository.findAll().isEmpty())
            throw new Exception("Watch Tower is Empty");
        watchTowerRepository.deleteAll();
    }

}
