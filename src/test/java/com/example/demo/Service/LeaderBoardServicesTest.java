package com.example.demo.Service;

import com.example.demo.model.LeaderBoard;
import com.example.demo.model.Users;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class LeaderBoardServicesTest {
LeaderBoard leaderBoard = null;
@Mock
LeaderBoardServices services;

@Rule public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setup(){
       leaderBoard = new LeaderBoard((List<Users>) services);

    }


    @Test
    public void getTopTenUsers()
    {

    }
}