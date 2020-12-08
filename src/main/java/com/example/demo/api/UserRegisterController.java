package com.example.demo.api;

import com.example.demo.Service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "Users")
@RestController
public class UserRegisterController
{

    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping(path = "/registerUser")
    public ResponseEntity<String> userRegistration(@RequestParam("username") String userName , @RequestParam("password") String passWord) throws Exception {
        String registrationMessage = userRegisterService.registerUser(userName,passWord);
        return new ResponseEntity<>(registrationMessage,HttpStatus.OK);
    }
}
