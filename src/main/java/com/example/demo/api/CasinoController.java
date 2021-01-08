package com.example.demo.api;

import com.example.demo.Service.CasinoService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/Casino")
@RestController
public class CasinoController {
    @Autowired
    private CasinoService casinoService;
    private User user;

    @PatchMapping(path = "/updateScore")
    public ResponseEntity updateScore(@RequestParam String userName)
        throws Exception {
        return new ResponseEntity<>(casinoService.casinoRisk(userName),HttpStatus.OK);
    }

    @GetMapping(value = "/getUpdatedScore")
    public Integer getUpdatedScore(@PathVariable int score){
        return user.getScore();
    }


}
