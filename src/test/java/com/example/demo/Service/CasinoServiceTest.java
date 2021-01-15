package com.example.demo.Service;

import static org.junit.Assert.*;
import com.example.demo.dao.LocationsRepositoryNew;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.LocationsNew;
import com.example.demo.model.User;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CasinoServiceTest {

    @MockBean
    LocationsRepositoryNew locationsRepositoryNew;

    @Autowired
    CasinoService casinoService;

    @MockBean
    UsersRepository usersRepository;

    @Test(expected = Exception.class)
    public void IfUserNameDoesNotExistThrowException() throws Exception {
        when(casinoService.casinoRisk("Sokra")).thenReturn(false);
        fail("Wrong id");
    }



}