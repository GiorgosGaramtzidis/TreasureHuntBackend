package com.example.demo.api;

import com.example.demo.dao.UsersRepository;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/Users")
@RestController
public class UsersController {

    @Autowired
    public UsersRepository usersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAllUsers(){

        return usersRepository.findAll();
    }

    @GetMapping(value = "/find/{id}")
    public Optional<Users> getUsersById(@PathVariable int id){

        return usersRepository.findById(id);
    }

    @GetMapping(value = "/find/score/{score}")
    public List<Users> getUsersWithScore(@PathVariable int score) {
        return usersRepository.getUsersWithScore(score);
    }

    @PostMapping(value = "/addUsers")
    public String saveUsers(@RequestBody Users users){
        usersRepository.save(users);
        return "Added users";
    }

    @DeleteMapping(value = "/delete")
    public String deleteAllUsers(){
        usersRepository.deleteAll();
        return "Delete All Users";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable int id){
        usersRepository.deleteById(id);
        return "Bye User";
    }
}
