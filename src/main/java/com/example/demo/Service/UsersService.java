package com.example.demo.Service;

import com.example.demo.Registration.IUserService;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService implements IUserService<UUID, User> {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Boolean registerUser(User user) throws Exception {
        if (usersRepository.existsByUserName(user.getUserName())) {
            throw new Exception("User with this username : "+user.getUserName()+" already exists");
        }
        usersRepository.save(user);
        return true;
    }

    @Override
    public Optional<User> getUser(String userName) throws Exception {
        if(usersRepository.existsByUserName(userName))
            usersRepository.findByUserName(userName);
        throw new Exception("User with this userName : "+userName+" doesn't exists");
    }

    public List<User> getAllUsers() throws Exception {
        return usersRepository.findAll();
    }

    @Override
    public User updateUser(User user) throws Exception {
        if (usersRepository.existsByUserName(user.getUserName()))
                throw new Exception("UserName used");
        else {
            usersRepository.save(user);
        }
        return user;
    }
    @Override
    public void deleteUser(User user) throws Exception {
        usersRepository.delete(user);
    }

    @Override
    public  Boolean loginConfirmation(String username, String password) throws Exception{
        if (usersRepository.existsByUserName(username)) {
            String UsersPassword =usersRepository.findUserByUserName(username).getPassword();
            if (UsersPassword.equals(password))
                return true;
            return false;
        }
        //return "User not found";
        throw new Exception("UserName doesn't Exists");
    }
}
