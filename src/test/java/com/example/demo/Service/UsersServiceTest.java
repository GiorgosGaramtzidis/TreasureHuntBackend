package com.example.demo.Service;

import com.example.demo.dao.UsersRepository;
import com.example.demo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

    @MockBean
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Test(expected = Exception.class)
    public void loginConfirmationWhenUserDoesNotExistShouldCreateException() throws Exception {

        when(usersRepository.existsByUserName("Konto4")).thenReturn(false);
        usersService.loginConfirmation("Konto4","3");
        fail("This should return User does not exist");
    }
    @Test
    public void loginConfirmationWhenUserDoesExistButPasswordIsWrongShouldReturnFalse() throws Exception {

        User user = new User();

        user.setPassword("foo");
        user.setUserName("blah");
        when(usersRepository.existsByUserName("blah")).thenReturn(true);
        when(usersRepository.findUserByUserName("blah")).thenReturn(user);

        Boolean actualResult = usersService.loginConfirmation("blah", "foo3");
        assertEquals(false,actualResult);
    }

    @Test
    public void loginConfirmationWhenUserDoesExistAndPasswordIsCorrect() throws Exception {

        User user = new User();

        user.setPassword("foo");
        user.setUserName("blah");
        when(usersRepository.existsByUserName("blah")).thenReturn(true);
        when(usersRepository.findUserByUserName("blah")).thenReturn(user);

        Boolean actualResult = usersService.loginConfirmation("blah", "foo");
        assertEquals(true,actualResult);
    }
}