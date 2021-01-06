package com.example.demo.api;

import com.example.demo.Service.WatchTowerService;
import com.example.demo.model.WatchTower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping(value = "/WatchTower")
@RestController
public class WatchTowerController {
    @Autowired
    private WatchTowerService watchTowerService;


    @GetMapping("/WatchTowerList")
    ResponseEntity getWatchTowerList() throws Exception{
        return new ResponseEntity<>(watchTowerService.getWatchTowerList(), HttpStatus.OK);
    }

    @DeleteMapping("/DeleteUserFromWatchTower")
    ResponseEntity deleteFromWatchTower(@RequestParam("userName")String userName) throws Exception{
        watchTowerService.deleteFromWatchTower(userName);
        HashMap<String,String> resp = new HashMap<>();
        resp.put("message", "User is successfully deleted");
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }
    /*
    @PatchMapping("/updateUserFromWatchTower")
    ResponseEntity updateUserFromWatchTower(@RequestParam("userName")String userName,@RequestParam("locationTitle") String locationTitle) throws Exception{
        return new ResponseEntity<>(watchTowerService.updateUserFromWatchTower(userName,locationTitle),HttpStatus.OK);
    }
     */

    @PostMapping("/addInWatchTower")
    ResponseEntity addInWatchTower(@RequestParam("userName")String userName , @RequestParam("locationTitle") String locationTitle) throws Exception{
        return new ResponseEntity<>(watchTowerService.addInWatchTower(userName,locationTitle),HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllFromWatchTower")
    ResponseEntity deleteAllFromWatchTower() throws Exception{
        watchTowerService.deleteAllFromWatchTower();
        HashMap<String,String> resp = new HashMap<>();
        resp.put("message", "Watch Tower Is Successful deleted");
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }
}
