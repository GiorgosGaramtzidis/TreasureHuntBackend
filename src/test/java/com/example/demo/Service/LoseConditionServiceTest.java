package com.example.demo.Service;

import com.example.demo.dao.UsersRepository;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoseConditionServiceTest {


    @MockBean
    UsersRepository usersRepository;

    @Autowired
    LoseConditionService loseConditionService;
    @Autowired
    UsersService usersService;

    @Test
    public void loseConditionIfUserExistsByUserNameAndUserLivesAreLessOrEqualToOneShouldReturnTrue() throws Exception{

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("Konto41");
        user.setUserLives(0);
        users.add(user);
        when(usersRepository.existsByUserName("Konto41")).thenReturn(true);
        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName()
                        .equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(users);
        Boolean actualResult = loseConditionService.loseCondition(user.getUserName());
        assertEquals(true,actualResult);
    }

    @Test(expected = Exception.class)
    public void loseConditionIfUserDoesNotExistsByUserNameShouldReturnException() throws Exception{

        when(usersRepository.existsByUserName("foo")).thenReturn(false);
        loseConditionService.loseCondition("foo");
        fail("This should return wrong id");
    }

    @Test
    public void loseConditionIfUserExistsByUserNameAndUserLivesAreMoreThanOneShouldReturnFalse() throws Exception{

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("Konto41");
        user.setUserLives(4);
        users.add(user);
        when(usersRepository.existsByUserName("Konto41")).thenReturn(true);
        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName()
                        .equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(users);
        Boolean actualResult = loseConditionService.loseCondition(user.getUserName());
        assertEquals(false,actualResult);
    }

    @Test(expected = Exception.class)
    public void loseConditionIfUserIsNullShouldReturnException() throws Exception{

        when(usersRepository.existsByUserName(null)).thenReturn(false);
        loseConditionService.loseCondition(null);
        fail("This should return wrong id");
    }

}