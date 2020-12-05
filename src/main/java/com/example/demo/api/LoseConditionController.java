package com.example.demo.api;

import com.example.demo.Service.LoseConditionService;

import com.example.demo.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RequestMapping(value = "/LoseCondition")
@RestController
public class LoseConditionController {
    @Autowired
    private LoseConditionService loseConditionService;
    private Users user;

    @PutMapping(path = "/updateUserLives")
    public ResponseEntity updateUserLives(@RequestBody Users user)
            throws Exception {

        return new ResponseEntity<>(loseConditionService.loseCondition(user), HttpStatus.OK);}

    @GetMapping(value = "/getUserLives")
    public Integer getUserLives(@PathVariable int userLives) {
        return user.getUserLives();
    }


}
