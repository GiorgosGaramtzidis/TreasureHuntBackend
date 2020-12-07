package com.example.demo.api;


import com.example.demo.Service.LeaderBoardServices;
import com.example.demo.model.LeaderBoard;
import com.example.demo.model.Locations;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping(value = "/LeaderBoard")
@RestController
public class LeaderBoardController {

    @Autowired
    private LeaderBoardServices leaderBoardServices;


    @GetMapping("/topTen")
    ResponseEntity getTopTenLeaderBoard() throws Exception{
        return new ResponseEntity<>(leaderBoardServices.getTopTenUsers(), HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity getLeaderBoard() throws Exception{
        return new ResponseEntity<>(leaderBoardServices.getLeaderBoard(), HttpStatus.OK);
    }

}
