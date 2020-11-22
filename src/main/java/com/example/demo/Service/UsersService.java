package com.example.demo.Service;

import com.example.demo.Registration.UsersRegistration;
import com.example.demo.dao.UsersRepositoryNew;
import com.example.demo.model.UsersNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements UsersRegistration<String, UsersNew> {
    @Autowired
    private UsersRepositoryNew usersRepositoryNew;
    @Override
    public Boolean registerUser(UsersNew user) throws Exception {
        if (usersRepositoryNew.existsById(user.getId())) {
            return false;
        }
        usersRepositoryNew.save(user);
        return true;
    }

    @Override
    public Optional<UsersNew> getUser(String userId) throws Exception {
        return usersRepositoryNew.findById(userId);
    }

    public List<UsersNew> getAllUser() throws Exception {
        List<UsersNew> users = usersRepositoryNew.findAll();
        return users;
    }

    @Override
    public UsersNew updateUser(UsersNew user) throws Exception {
        user = usersRepositoryNew.save(user);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        if (userId == null) {
            throw new Exception("user id is null");
        } else {
            usersRepositoryNew.deleteById(userId);
        }
    }
}
