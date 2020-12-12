package com.example.demo.Service;

import com.example.demo.model.LeaderBoardUser;
import com.example.demo.model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

public class LeaderBoardUserServicesTest {
LeaderBoardUser leaderBoardUser = null;
@Mock
LeaderBoardServices services;

@Rule public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setup(){


    }


    @Test
    public void getTopTenUsers()
    {

    }
}