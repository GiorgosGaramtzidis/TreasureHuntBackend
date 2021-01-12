package com.example.demo.Service;

import com.example.demo.dao.WatchTowerRepository;
import com.example.demo.model.WatchTower;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WatchTowerServiceTest {
    @Autowired
    WatchTowerService watchTowerService;

    @MockBean
    WatchTowerRepository watchTowerRepository;

    @Test
    public void getAllUserWhenListIsNotEmptyThisShouldReturnAList() throws Exception{
        List<WatchTower> watchTowerList = new ArrayList<>();
        WatchTower watchTower = new WatchTower();
        watchTower.setUserName("Giwrgos");
        watchTower.setLocationTitle("location");
        watchTowerList.add(watchTower);
        when(watchTowerRepository.findAll()).thenReturn(watchTowerList);
        watchTowerService.getWatchTowerList();
    }

    @Test(expected = Exception.class)
    public void getAllUserWhenListIsEmptyThisShouldCreateException() throws Exception{
        List<WatchTower> watchTowerList = new ArrayList<>();
        when(watchTowerRepository.findAll()).thenReturn(watchTowerList);
        watchTowerService.getWatchTowerList();
        fail("Collection is empty");
    }

    @Test
    public void addInWatchTowerWhenUserDoesntExistsShouldAddCurrentUser() throws Exception{
        when(watchTowerRepository.existsByUserName("Giwrgos")).thenReturn(false);
        watchTowerService.addInWatchTower("Giwrgos","Start");
    }

    @Test(expected = Exception.class)
    public void addInWatchTowerWhenUserNameIsNullShouldCreateException() throws Exception{
        when(watchTowerRepository.existsByUserName(null)).thenReturn(false);
        watchTowerService.addInWatchTower(null,"Start");
        fail("User Name is Null");
    }

    @Test
    public void addInWatchTowerWhenUserDoesExistsAndUserNameIsNotNullShouldUpdateCurrentUser() throws Exception{
        List<WatchTower> watchTowerList = new ArrayList<>();
        WatchTower watchTower = new WatchTower();
        watchTower.setUserName("Giwrgos");
        watchTower.setLocationTitle("location");
        watchTowerList.add(watchTower);
        when(watchTowerRepository.existsByUserName("Giwrgos")).thenReturn(true);
        watchTowerService.addInWatchTower("Giwrgos","Start");
    }

    @Test(expected = Exception.class)
    public void resetWatchTowerWhenItsEmptyShouldReturnException() throws Exception {
        List<WatchTower> watchTowerList = new ArrayList<>();
        when(watchTowerRepository.findAll()).thenReturn(watchTowerList);
        watchTowerService.resetWatchTower();
        fail("Collection is empty");
    }
    @Test
    public void resetWatchTowerWhenItsNotEmptyShouldTrue() throws Exception {
        List<WatchTower> watchTowerList = new ArrayList<>();
        WatchTower watchTower = new WatchTower();
        watchTower.setUserName("Giwrgos");
        watchTower.setLocationTitle("location");
        watchTowerList.add(watchTower);
        when(watchTowerRepository.findAll()).thenReturn(watchTowerList);
        assertEquals(true,watchTowerService.resetWatchTower());
    }
}