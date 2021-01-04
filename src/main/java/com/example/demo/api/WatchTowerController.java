package com.example.demo.api;

import com.example.demo.Service.WatchTowerService;
import com.example.demo.model.WatchTower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/WatchTower")
@RestController
public class WatchTowerController {
    @Autowired
    private WatchTowerService watchTowerService;


    @GetMapping("/WatchTowerList")
    ResponseEntity getWatchTowerList() throws Exception{
        return new ResponseEntity<>(watchTowerService.getWatchTowerList(), HttpStatus.OK);
    }

    @PatchMapping("/updateWatchTower")
    ResponseEntity updateWatchTower(@RequestParam("userName")String userName , @RequestParam("locationTitle") String locationTitle) throws Exception{
        return new ResponseEntity<>(watchTowerService.updateWatchTower(userName,locationTitle),HttpStatus.OK);
    }

    @PostMapping("/addWatchTower")
    ResponseEntity addWatchTower(@RequestParam("userName")String userName , @RequestParam("locationTitle") String locationTitle) throws Exception{
        return new ResponseEntity<>(watchTowerService.addWatchTower(userName,locationTitle),HttpStatus.OK);
    }
}
