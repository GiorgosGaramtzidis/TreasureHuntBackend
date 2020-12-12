package com.example.demo.api;

import com.example.demo.Service.UserLoginService;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserLoginController.class)
public class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserLoginService userLoginService;

    @MockBean
    UsersRepository usersRepository;

    String url ="/UserLogin/login";

    @Test
    public void TestWhenABadLoginRequestArrives() throws Exception {
        mockMvc.perform(get(url)).andExpect(status().isBadRequest()).andReturn();
    }
    @Test
    public void TestWhenARequestComesWithWrongPath()throws Exception
    {
        mockMvc.perform(get(url)).andExpect(status().is(404));
    }
    @Test
    public void TestWhenARequestComesWithCorrectParamsAndLogin() throws Exception {
        Mockito.when(userLoginService.confirmLogin("George1", "12345FK@")).thenReturn(new User("George1","12345FK@"));
        Mockito.when(usersRepository.findUserByUserName("George1")).thenReturn(new User("George1","12345FK@"));
        Mockito.when(usersRepository.existsByUserName("George1")).thenReturn(true);
        mockMvc.perform((get(url).param("username","George1")).param("password","12345FK@")).andExpect(status().isOk());
    }
    @Test(expected = Exception.class)
    public void TestWhenRequestComesWithCorrectPramsButCantLoginCauseOfPassword() throws Exception {
        Mockito.when(userLoginService.confirmLogin("George1","12345FK@")).thenThrow(new Exception());
        Mockito.when(usersRepository.findUserByUserName("George1")).thenReturn(new User("George1","123"));
        Mockito.when(usersRepository.existsByUserName("George1")).thenReturn(false);
        mockMvc.perform((get(url).param("username","George1")).param("password","12345FK@")).andExpect(status().is(500));
    }

}