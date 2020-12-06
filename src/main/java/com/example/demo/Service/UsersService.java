package com.example.demo.Service;

import com.example.demo.Registration.IUserService;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Optional<Users> getUser(String userId) throws Exception {
        return usersRepository.findById(userId);
    }

    public List<Users> getAllUser() throws Exception {
        List<Users> users = usersRepository.findAll();
        return users;
    }

    @Override
    public Users updateUser(Users user) throws Exception {
        user = usersRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        if (userId == null) {
            throw new Exception("user id is null");
        } else {
            usersRepository.deleteById(userId);
        }
    }


    @Override
    public int addScore(String userName,int score) throws Exception{
        if(usersRepository.existsByUserName(userName)) {
            List<Users> user =usersRepository.findAll().stream().filter(users ->users.getUserName().equals(userName))
                    .collect(Collectors.toList());
            user.get(0).setScore(user.get(0).getScore()+score);
            usersRepository.save(user.get(0));
            return 1;
        }
        throw new Exception("User does not exist");
    }


}
