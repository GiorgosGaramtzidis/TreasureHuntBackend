package com.example.demo.api;

import com.example.demo.Service.UserRegisterService;
import com.example.demo.TreasureHuntApplication;
import com.example.demo.dao.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserRegisterController.class)
@ContextConfiguration(classes= TreasureHuntApplication.class)
public class UserRegisterControllerTest
{
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private UserRegisterService userRegisterService;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    public void testWhenPostReturnsInvalidInputs() throws Exception
    {
        Mockito.when(userRegisterService.registerUser("George","12345")).thenReturn("Invalid inputs");
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(false);

        String url = "/Users/registerUser";
        MvcResult mvcResult =  mockMvc.perform(post(url)
                .param("username","George")
                .param("password","12345")
        ).andExpect(status().isOk()).andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        assertEquals("Invalid inputs",result);
    }
    @Test
    public void testWhenPostContainsReturnsSuccessRegistration() throws Exception {

        Mockito.when(userRegisterService.registerUser("George1", "12345FK@")).thenReturn("User register successfully");
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(false);

        String url = "/Users/registerUser";
        MvcResult mvcResult = mockMvc.perform(post(url)
                .param("username", "George1")
                .param("password", "12345FK@")
        ).andExpect(status().isOk()).andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        assertEquals("User register successfully", result);
    }
    @Test
    public void testWhenARequestComesWithNoParams() throws Exception {
        Mockito.when(userRegisterService.registerUser("George1", "12345FK@")).thenReturn("User register successfully");
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(false);

        String url = "/Users/registerUser";
         mockMvc.perform(post(url)
        ).andExpect(status().isBadRequest()).andReturn();
    }
}