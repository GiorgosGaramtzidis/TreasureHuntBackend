package com.example.demo.Registration;

import com.example.demo.model.Status;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;


public interface ILoginService
{
    User confirmLogin(String username , String passWord)throws Exception;

    void LogOutUser(String username);

    void updateUserStatus(String username, Status status);
}
