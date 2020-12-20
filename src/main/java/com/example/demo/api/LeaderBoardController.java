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

@PostMapping("/addLeaderBoardUser")
ResponseEntity addLeaderBoardUser(@RequestBody LeaderBoardUser leaderBoardUser) throws Exception {
    return new ResponseEntity<>(leaderBoardServices.addLeaderBoardUser(leaderBoardUser),HttpStatus.OK);
}

    @PatchMapping(path = "/updateLeaderBoard")
    public ResponseEntity update(@RequestParam String leaderBoardName,@RequestParam int score)
            throws Exception {

        return new ResponseEntity<>(leaderBoardServices.updateLeaderBoard(leaderBoardName,score),HttpStatus.OK);}


    @GetMapping("/all")
    ResponseEntity getLeaderBoard() throws Exception{
        return new ResponseEntity<>(leaderBoardServices.getLeaderBoard(), HttpStatus.OK);
    }

}
