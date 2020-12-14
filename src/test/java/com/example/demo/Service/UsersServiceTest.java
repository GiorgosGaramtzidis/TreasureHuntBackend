package com.example.demo.Service;

import com.example.demo.dao.UsersRepository;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
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

    @Test(expected = Exception.class)
    public void IfUserNameExistAndNewUserNameUsed() throws Exception{
        User user = new User();

        user.setPassword("password");
        user.setUserName("Fragkos");
        when(usersRepository.existsByUserName("Sokra")).thenReturn(true);
        when(usersRepository.findUserByUserName("Fragkos")).thenReturn(user);

        usersService.changeName("Sokra","Fragkos");
    }
    @Test(expected = Exception.class)
    public void IfUserNameNotExistAndNewUserNameUsed() throws Exception{
        User user = new User();

        user.setPassword("pass");
        user.setUserName("Sokra1212");
        when(usersRepository.existsByUserName("Sokra")).thenReturn(false);

        usersService.changeName("Sokra1212", " Sokra");
    }
    @Test
    public void IfUserNameExistAndNewUserNameNotExist () throws Exception {
        User user = new User();

        user.setPassword("pass");
        user.setUserName("Sokra1212");
        when(usersRepository.existsByUserName("Sokra1212")).thenReturn(true);
        when(usersRepository.existsByUserName("Konto12")).thenReturn(false);
        when(usersRepository.findUserByUserName("Sokra1212")).thenReturn(user);

        usersService.changeName("Sokra1212","Konto12");
        assertEquals("Konto12",user.getUserName());
    }
    @Test(expected = Exception.class)
    public void IfUserNameNotExistInPasswordChange() throws Exception{
        User user = new User();

        user.setPassword("password");
        user.setUserName("Sokratis");
        when(usersRepository.existsByUserName("Sokra")).thenReturn(false);

        usersService.changePassword("Sokra","Fragkos");
    }

    @Test
    public void IfUserNameExistGoChangePassword () throws Exception {
        User user = new User();
        user.setPassword("Nothing");
        user.setUserName("Sokra");

        when(usersRepository.existsByUserName("Sokra")).thenReturn(true);
        when(usersRepository.findUserByUserName("Sokra")).thenReturn(user);

        usersService.changePassword("Sokra","newPassword");
        assertEquals("newPassword",user.getPassword());
    }
}