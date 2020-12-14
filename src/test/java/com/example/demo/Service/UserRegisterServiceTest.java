package com.example.demo.Service;

import com.example.demo.dao.UsersRepository;
import com.example.demo.model.RegistrationAnswer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegisterServiceTest {

    @MockBean
    UsersRepository usersRepository;

    @Autowired
    UserRegisterService userRegisterService;

    @Test
    public void testWhenUserNameAlreadyExists(){
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(true);
        RegistrationAnswer registrationAnswer  =userRegisterService.registerUser("George","123456@4Fk");
        assertEquals("User with this userName already exists",registrationAnswer.getAnswer());
    }
    @Test
    public void testWhenPassWordDoesntFollowTheRules() {
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(false);
        RegistrationAnswer registrationAnswer =userRegisterService.registerUser("George123","12345");
        assertEquals("Invalid inputs",registrationAnswer.getAnswer());
    }
    @Test
    public void testWhenUserNameDoesntFollowTheRules() {
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(false);
        RegistrationAnswer registrationAnswer =userRegisterService.registerUser("George","12345Gf1@");
        assertEquals("Invalid inputs",registrationAnswer.getAnswer());
    }
    @Test
    public  void testWhenUserNameAndPassWordFollowTheRules() {
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(false);
        RegistrationAnswer registrationAnswer=userRegisterService.registerUser("George123FK","12345Gf1@");
        assertEquals("User register successfully",registrationAnswer.getAnswer());
    }
    @Test(expected = Exception.class)
    public void testWhenUserNameIsNull(){
        RegistrationAnswer registrationAnswer =userRegisterService.registerUser(null,"12345Gf1@");
        assertEquals("User with this userName already exists",registrationAnswer.getAnswer());
    }


}