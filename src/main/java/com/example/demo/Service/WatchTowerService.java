package com.example.demo.Service;

import com.example.demo.Registration.WatchTowerRegistration;
import com.example.demo.dao.WatchTowerRepository;
import com.example.demo.model.User;
import com.example.demo.model.UserState;
import com.example.demo.model.WatchTower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
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
    public WatchTower addInWatchTower(String userName, String locationTitle) throws Exception {
        WatchTower watchTower = new WatchTower();
        watchTower.setUserName(userName);
        watchTower.setLocationTitle(locationTitle);
        if (watchTowerRepository.existsByUserName(userName)) {
            watchTowerRepository.deleteByUserName(userName);
            return watchTowerRepository.save(watchTower);
        } else if (watchTowerRepository.existsByUserName(userName)==false && userName!=null) {
            return watchTowerRepository.save(watchTower);
        }
        throw new Exception("User  Name is Null");
    }

    @Override
    public Boolean resetWatchTower() throws Exception {
        if (watchTowerRepository.findAll().isEmpty())
            throw new Exception("Watch Tower is already empty");
        watchTowerRepository.deleteAll();
        return true;
    }
}
