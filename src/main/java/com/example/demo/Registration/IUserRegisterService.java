package com.example.demo.Registration;

import com.example.demo.model.RegistrationAnswer;

public interface IUserRegisterService
{
   RegistrationAnswer registerUser(String userName , String passWord);

}
