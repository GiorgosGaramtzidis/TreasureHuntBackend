package com.example.demo.Service;

import static org.junit.Assert.*;
import com.example.demo.dao.LocationsRepositoryNew;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.LocationsNew;
import com.example.demo.model.User;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CasinoServiceTest {

    @MockBean
    LocationsRepositoryNew locationsRepositoryNew;

    @Autowired
    CasinoService casinoService;

    @MockBean
    UsersRepository usersRepository;

    protected int a;
    @Test(expected = Exception.class)
    public void IfUserNameDoesNotExistThrowException() throws Exception {
        when(casinoService.casinoRisk("Sokra")).thenReturn(false);
        fail("Wrong id");
    }

    @Before
    public void setup(){
        Random random = new Random();
        random= Mockito.mock(Random.class);
        a = casinoService.rollDice();
    }

    @Test
    public void test () throws Exception {
        User user = new User();
        List<User> list = new ArrayList<>();
        user.setScore(10);
        user.setUserName("Sokra");
        list.add(user);
        when(usersRepository.existsByUserName(user.getUserName())).thenReturn(true);
        when(usersRepository.findAll().stream().filter(user1 -> user1.getUserName().equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(list);
        if(a==0){
            when(casinoService.casinoRisk(list.get(0).getUserName()) && a==0).thenReturn(false);
        }
        else
            when(casinoService.casinoRisk(list.get(0).getUserName()) && a==1).thenReturn(true);
    }

    @Test
    public void test1 () throws Exception {
        User user = new User();
        List<User> list = new ArrayList<>();
        user.setScore(10);
        user.setUserName("Sokra");
        list.add(user);
        when(usersRepository.existsByUserName(user.getUserName())).thenReturn(true);
        when(usersRepository.findAll().stream().filter(user1 -> user1.getUserName().equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(list);
        if(a==0){
            when(casinoService.casinoRisk(list.get(0).getUserName()) && casinoService.rollDice()==0).thenReturn(false);
        }
        else
            when(casinoService.casinoRisk(list.get(0).getUserName()) && casinoService.rollDice()==1).thenReturn(true);
    }
}