package com.example.demo.Service;

import com.example.demo.dao.UsersRepository;
import com.example.demo.model.User;
import com.example.demo.model.UserState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test(expected = Exception.class)
    public void restartScoreAndLivesWhenUserDoesNotExistShouldCreateException() throws Exception {

        when(usersRepository.existsByUserName("Konto4")).thenReturn(false);
        usersService.restartScoreAndLives("Konto4");
        fail("This should return User does not exist");
    }

    @Test
    public void restartScoreAndLivesWhenUserDoesExistShouldReturnFalse() throws Exception {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("blah");
        users.add(user);

        when(usersRepository.existsByUserName(user.getUserName())).thenReturn(true);
        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName().equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(users);


        Boolean actualResult = usersService.restartScoreAndLives("blah");
        assertEquals(true,actualResult);
    }
    @Test
    public void setUserStateWhenLocationTitleIsEND() throws Exception {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("Marko1996");
        users.add(user);

        when(usersRepository.existsByUserName("Marko1996")).thenReturn(true);
        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName().equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(users);
        Boolean actualResult = usersService.setUserState("Marko1996", "end");
        assertEquals(true,actualResult);
    }

    @Test
    public void setUserStateWhenLocationTitleIsNotEND() throws Exception {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("Marko1996");
        users.add(user);

        when(usersRepository.existsByUserName("Marko1996")).thenReturn(true);
        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName().equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(users);
        Boolean actualResult = usersService.setUserState("Marko1996", "Gate");
        assertEquals(false,actualResult);
    }

    @Test
    public void checkUserStateIfSomeonesStateIsWINShouldReturnUserThatHasStateWIN() throws Exception {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("Marko1996");
        user.setUserState(UserState.WIN);
        users.add(user);

        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserState()
                        .equals(UserState.WIN))
                .collect(Collectors.toList())).thenReturn(users);
        String actualResult = usersService.checkUserState();
        assertEquals("Marko1996",actualResult);
    }

    @Test
    public void checkUserStateIfNonesStateIsWINShouldReturnPLAYING() throws Exception {

        List<User> users = new ArrayList<>();

        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserState()
                        .equals(UserState.WIN))
                .collect(Collectors.toList())).thenReturn(users);
        String actualResult = usersService.checkUserState();
        assertEquals("PLAYING",actualResult);
    }
}