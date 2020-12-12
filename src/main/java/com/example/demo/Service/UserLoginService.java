package com.example.demo.Service;

import com.example.demo.Registration.ILoginService;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.Status;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserLoginService implements ILoginService
{
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User confirmLogin(String username , String password)throws Exception
    {
        if (usersRepository.existsByUserName(username))
        {
            if (password.equals(usersRepository.findUserByUserName(username).getPassword())){
                updateUserStatus(username,Status.Connected);
                return usersRepository.findUserByUserName(username);
            }
                throw new Exception("Wrong password");
        }
        throw new Exception("Username doesn't exists");
    }


    @Override
    public void LogOutUser(String username)
    {
        updateUserStatus(username,Status.Away);
        usersRepository.findUserByUserName(username).setStatus(Status.Away);
    }

    @Override
    public void updateUserStatus(String username,Status stattus)
    {
        User user =usersRepository.findUserByUserName(username);
        user.setStatus(stattus);
        usersRepository.save(user);
    }
}
