package com.example.demo.Service;

import com.example.demo.Registration.IUserService;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService implements IUserService<UUID, Users> {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Boolean registerUser(Users user) throws Exception {
        if (usersRepository.existsByUserName(user.getUserName())) {
            throw new Exception("User with this username : "+user.getUserName()+" already exists");
        }
        usersRepository.save(user);
        return true;
    }

    @Override
    public Optional<Users> getUser(String userName) throws Exception {
        if(usersRepository.existsByUserName(userName))
            usersRepository.findByUserName(userName);
        throw new Exception("User with this userName : "+userName+" doesn't exists");
    }

    public List<Users> getAllUser() throws Exception {
        return usersRepository.findAll();
    }

    @Override
    public Users updateUser(Users user) throws Exception {
        if (usersRepository.existsByUserName(user.getUserName()))
                throw new Exception("UserName used");
        else
            return usersRepository.save(user);
    }

    @Override
    public void deleteUser(Users user) throws Exception {
        usersRepository.delete(user);
    }


}
