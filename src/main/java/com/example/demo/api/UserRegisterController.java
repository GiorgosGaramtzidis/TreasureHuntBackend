package com.example.demo.api;

import com.example.demo.Service.UserRegisterService;
import com.example.demo.model.RegistrationAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/UserRegistration")
@RestController
public class UserRegisterController
{

    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping (path = "/registerUser")
    public ResponseEntity userRegistration(@RequestParam("username") String userName , @RequestParam("password") String passWord){
        RegistrationAnswer registrationMessage = userRegisterService.registerUser(userName,passWord);
        return new ResponseEntity<>(registrationMessage,HttpStatus.OK);
    }
}
