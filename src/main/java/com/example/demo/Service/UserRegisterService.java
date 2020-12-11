package com.example.demo.Service;

import com.example.demo.Registration.IUserRegisterService;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.RegistrationAnswer;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService implements IUserRegisterService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public RegistrationAnswer registerUser(String userName , String passWord)
    {
        RegistrationAnswer registrationAnswer= new RegistrationAnswer();
        if (usersRepository.existsByUserName(userName)){
            registrationAnswer.setAnswer("User with this userName already exists");
            return registrationAnswer;
        }
        if (passWordValidator(passWord) && userNameValidator(userName))
            {
                usersRepository.save(new User(userName,passWord));
                 registrationAnswer.setAnswer("User register successfully");
                 return registrationAnswer;
            }
        registrationAnswer.setAnswer("Invalid inputs");
        return registrationAnswer;
    }

    public Boolean passWordValidator(String passWord)
    {
        final String pattern = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,}$";
        return passWord.matches(pattern);
    }

    public Boolean userNameValidator(String userName)
    {
        final String pattern = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{5,20}$";
        return userName.matches(pattern);
    }
}
