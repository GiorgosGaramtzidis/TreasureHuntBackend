package com.example.demo.api;

import com.example.demo.Service.UserLoginService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/UserLogin")
@RestController
public class UserLoginController
{
    @Autowired
    private UserLoginService userLoginService;

    @GetMapping(path = "/login")
    public ResponseEntity loginUser(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        User user = userLoginService.confirmLogin(username, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping(path = "/logout")
    public void Logout(String  username)
    {
        userLoginService.LogOutUser(username);
    }
}
