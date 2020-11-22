package com.example.demo.api;

import com.example.demo.Service.UsersService;
import com.example.demo.model.UsersNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersControllerNew {
    @Autowired
    private UsersService usersService;

    @PostMapping(path = "/registerUser")
    public ResponseEntity registerUser(@RequestBody UsersNew user) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        if (usersService.registerUser(user)){
            resp.put("user", user);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        resp.put("user", user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @GetMapping("/getUser" )
    public ResponseEntity getUser(@RequestParam("id") String id) throws Exception {
        Optional<UsersNew> user = usersService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/getAllUser" )
    public ResponseEntity getAllUser() throws Exception {
        List<UsersNew> users = usersService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping(path = "/updateUser")
    public ResponseEntity updateUser(@RequestParam("id") String id, @RequestBody UsersNew user)
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
}
