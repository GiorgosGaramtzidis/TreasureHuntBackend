package com.example.demo.api;

import com.example.demo.Service.UsersService;
import com.example.demo.model.Users;
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

    @PostMapping(path = "/registerUser")
    public ResponseEntity registerUser(@RequestBody Users user) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        if (usersService.registerUser(user)){
            resp.put("user", user);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        resp.put("user", user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(path="/getUser" )
    public ResponseEntity getUser(@RequestParam("userName") String userName) throws Exception {
        Optional<Users> user = usersService.getUser(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping(path="/getAllUser" )
    public ResponseEntity getAllUser() throws Exception {
        List<Users> users = usersService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping(path = "/updateUser")
    public ResponseEntity updateUser(@RequestParam("id") String id, @RequestBody Users user)
            throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        usersService.updateUser(user);
        resp.put("user", user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity deleteUser(@RequestBody Users user) throws Exception {
        HashMap<String,String> resp = new HashMap<>();
        usersService.deleteUser(user);
        resp.put("Message","User succesfully deleted");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
