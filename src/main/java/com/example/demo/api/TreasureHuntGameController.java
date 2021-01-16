package com.example.demo.api;

import com.example.demo.Service.TreasureHuntGameService;
import com.example.demo.Service.UsersService;
import com.example.demo.model.*;
import net.bytebuddy.asm.Advice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/TreasureHuntGame")
@RestController
public class TreasureHuntGameController {

    @Autowired
    private TreasureHuntGameService treasureHuntGameService;


    @PostMapping(path = "/createGame")
     public ResponseEntity createGame(@RequestParam("id")String id,@RequestParam("gameName")String gameName,@RequestParam("gameLocation")String gameLocation) throws Exception {
        return new ResponseEntity<> (treasureHuntGameService.createGame(id,gameName,gameLocation),HttpStatus.OK);
    }

    @GetMapping("/getGame" )
    public ResponseEntity getTreasureHuntGame(@RequestParam("id") String id) throws Exception {
        Optional<TreasureHuntGame> treasureHuntGame = treasureHuntGameService.getGame(id);
        return new ResponseEntity<>(treasureHuntGame, HttpStatus.OK);
    }

    @PatchMapping("/addUser")
    public ResponseEntity addUser(@RequestParam ("userName") String  userName,@RequestParam("id") String id) throws Exception {
        return new ResponseEntity<>(treasureHuntGameService.addUser(userName,id),HttpStatus.OK);
    }


    @PatchMapping("/addLocation")
    public ResponseEntity addLocation(@RequestBody GameLocation gameLocation, @RequestParam("id") String id) throws Exception {
        return new ResponseEntity<>(treasureHuntGameService.addLocation(gameLocation,id),HttpStatus.OK);
    }

    @PatchMapping("setWinner")
    public void setWinner(@RequestBody User user,@RequestParam("id")String id) throws Exception{
       treasureHuntGameService.setWinner(user,id);
    }


}
