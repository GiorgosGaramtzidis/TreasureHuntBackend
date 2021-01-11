package com.example.demo.api;

import com.example.demo.Service.UserRegisterService;
import com.example.demo.TreasureHuntApplication;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.RegistrationAnswer;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    public void testWhenRequestComesWithWrongUrl() throws Exception
    {
        String url = "/UserRegistration1/registerUser";
        MvcResult mvcResult =  mockMvc.perform(post(url)
                .param("username","George")
                .param("password","12345")
        ).andExpect(status().is(404)).andReturn();
    }
    @Test
    public void testWhenRequestComesWithCorrectParamsButUserDontRegister() throws Exception {

        Mockito.when(userRegisterService.registerUser("George1", "12345FK@")).thenReturn(new RegistrationAnswer("Invalid inputs"));
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(false);

        String url = "/UserRegistration/registerUser";
        MvcResult mvcResult = mockMvc.perform(post(url)
                .param("username", "George1")
                .param("password", "12345FK@")

        ).andExpect(content().json("{'answer':'Invalid inputs'}")).andReturn();

        int result =mvcResult.getResponse().getStatus();
        assertEquals(200 ,result);
    }
    @Test
    public void testWhenARequestComesWithNoParams() throws Exception {

        String url = "/UserRegistration/registerUser";
         mockMvc.perform(post(url)
        ).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void testWhenRequestIsSuccessfulAndAUserRegister() throws Exception {

        Mockito.when(userRegisterService.registerUser("George1", "12345FK@")).thenReturn(new RegistrationAnswer("User register successfully"));
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(false);

        String url = "/UserRegistration/registerUser";
        MvcResult mvcResult = mockMvc.perform(post(url)
                .param("username", "George1")
                .param("password", "12345FK@")
        ).andExpect(content().json("{'answer':'User register successfully'}")).andReturn();
        int response = mvcResult.getResponse().getStatus();
        assertEquals(200,response);
    }
    @Test
    public void testWhenRequestIsSuccessfulButUserNameAlreadyExists() throws Exception {

        Mockito.when(userRegisterService.registerUser("George1", "12345FK@")).thenReturn(new RegistrationAnswer("User with this userName already exists"));
        Mockito.when(usersRepository.existsByUserName("George")).thenReturn(false);

        String url = "/UserRegistration/registerUser";
        MvcResult mvcResult = mockMvc.perform(post(url)
                .param("username", "George1")
                .param("password", "12345FK@")

        ).andExpect(content().json("{'answer':'User with this userName already exists'}")).andReturn();
        int response = mvcResult.getResponse().getStatus();
        assertEquals(200,response);
    }
}