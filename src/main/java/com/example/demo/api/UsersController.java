package com.example.demo.api;

import com.example.demo.Service.UsersService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@RequestMapping(value = "/Users")
@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/getUser" )
    public ResponseEntity getUser(@RequestParam("id") String id) throws Exception {
        Optional<User> user = usersService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/getAllUser" )
    public ResponseEntity getAllUser() throws Exception {
        List<User> users = usersService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping(path = "/updateUser")
    public ResponseEntity updateUser(@RequestParam("id") String id, @RequestBody User user)
            throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        usersService.updateUser(user);
        resp.put("user", user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestParam("id") String id) throws Exception {
        usersService.deleteUser(id);
        HashMap<String,String> resp = new HashMap<>();
        resp.put("message", "User is successfully deleted");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @PatchMapping("/addScore")
    public ResponseEntity addScore(@RequestParam String userName,@RequestParam("score") int score) throws Exception {
        return new ResponseEntity<>(usersService.addScore(userName,score),HttpStatus.OK);
    }
    @GetMapping("/getUserScore")
    public ResponseEntity getUserScore(@RequestParam ("userName") String userName) throws Exception {
        return new ResponseEntity<>(usersService.getUserScore(userName),HttpStatus.OK);
    }

    @GetMapping(path = "/loginUser")
    public ResponseEntity loginUser(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception{
        return new ResponseEntity<>(usersService.loginConfirmation(username,password),HttpStatus.OK);
    }

}
