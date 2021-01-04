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
    public WatchTower updateWatchTower(String userName, String locationTitle) throws Exception {
        if(watchTowerRepository.existsByUserName(userName)) {
            List<WatchTower> watchTowerList = watchTowerRepository.findAll().stream().filter(watchTower -> watchTower.getUserName().equals(userName))
                    .collect(Collectors.toList());
            watchTowerList.get(0).setLocationTitle(locationTitle);
            return watchTowerRepository.save(watchTowerList.get(0));
        }
        throw new Exception("Wrong User Name");
    }

    @Override
    public WatchTower addWatchTower(String userName, String locationTitle) throws Exception {
        WatchTower watchTower = new WatchTower();
        watchTower.setUserName(userName);
        watchTower.setLocationTitle(locationTitle);
        if(watchTowerRepository.existsByUserName(userName))
                throw new Exception("User Name Already Exists"+userName);
        return watchTowerRepository.save(watchTower);
    }
}
