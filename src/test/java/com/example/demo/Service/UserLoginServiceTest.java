package com.example.demo.Service;

import com.example.demo.dao.UsersRepository;
import com.example.demo.model.Status;
import com.example.demo.model.User;
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
public class UserLoginServiceTest {

    @Autowired
    private UserLoginService userLoginService;

    @MockBean
    private UsersRepository usersRepository;

    @Test(expected = Exception.class)
    public void testWhenUserTriesToLogInWithAWrongUserName() throws Exception
    {
        String username = "Konto123";
        String password = "1234@1fK";

        Mockito.when(usersRepository.existsByUserName(username)).thenReturn(false);

        userLoginService.confirmLogin(username,password);
    }
    @Test(expected = Exception.class)
    public void testWhenUserTriesToLogInWithWrongPassWord() throws Exception
    {
        String username = "Konto3";
        String password = "1234@1fK";

        Mockito.when(usersRepository.existsByUserName(username)).thenReturn(true);
        Mockito.when(usersRepository.findUserByUserName(username)).thenReturn(new User("Konto3","123456@"));

        userLoginService.confirmLogin(username,password);
    }
    @Test
    public void testWhenUserLogIn() throws Exception {
        String username = "Konto3";
        String password = "123";

        Mockito.when(usersRepository.existsByUserName(username)).thenReturn(true);
        Mockito.when(usersRepository.findUserByUserName(username)).thenReturn(new User("Konto3", "123"));


        User user = userLoginService.confirmLogin(username, password);

        assertEquals(user.getStatus(), Status.Connected);

    }
}