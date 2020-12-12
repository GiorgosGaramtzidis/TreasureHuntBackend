package com.example.demo.api;


import com.example.demo.Service.LeaderBoardServices;
import com.example.demo.model.LeaderBoardUser;
import com.example.demo.model.LocationsNew;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/LeaderBoard")
@RestController
public class LeaderBoardController {

    @Autowired
    private LeaderBoardServices leaderBoardServices;

/*
    @GetMapping("/topTen")
    ResponseEntity getTopTenLeaderBoard() throws Exception{
        return new ResponseEntity<>(leaderBoardServices.getTopTenUsers(), HttpStatus.OK);
    }

 */
@PostMapping("/addLeaderBoardUser")
ResponseEntity addLeaderBoardUser(@RequestBody LeaderBoardUser leaderBoardUser) throws Exception {
    return new ResponseEntity<>(leaderBoardServices.addLeaderBoardUser(leaderBoardUser),HttpStatus.OK);
}



@PutMapping("/updateLeaderBoard")
ResponseEntity updateLeaderBoard(@RequestParam String name, @RequestBody LeaderBoardUser leaderBoardUser) throws Exception{
    leaderBoardUser.setLeaderBoardName(name);
    return new ResponseEntity<>(leaderBoardServices.updateLeaderBoardUser(name,leaderBoardUser),HttpStatus.OK);

}



    @GetMapping("/all")
    ResponseEntity getLeaderBoard() throws Exception{
        return new ResponseEntity<>(leaderBoardServices.getLeaderBoard(), HttpStatus.OK);
    }

}
