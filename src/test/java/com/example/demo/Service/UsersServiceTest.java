package com.example.demo.Service;


import com.example.demo.dao.QuestionsRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

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

    @MockBean
    QuestionsRepository questionsRepository;

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
    public void IfUserNameExistAndNewUserNameUsed() throws Exception {
        User user = new User();

        user.setPassword("password");
        user.setUserName("Fragkos");
        when(usersRepository.existsByUserName("Sokra")).thenReturn(true);
        when(usersRepository.findUserByUserName("Fragkos")).thenReturn(user);

        usersService.changeName("Sokra", "Fragkos");
    }

    @Test(expected = Exception.class)
    public void IfUserNameNotExistAndNewUserNameUsed() throws Exception {
        User user = new User();

        user.setPassword("pass");
        user.setUserName("Sokra1212");
        when(usersRepository.existsByUserName("Sokra")).thenReturn(false);

        usersService.changeName("Sokra1212", " Sokra");
    }

    @Test
    public void IfUserNameExistAndNewUserNameNotExist() throws Exception {
        User user = new User();

        user.setPassword("pass");
        user.setUserName("Sokra1212");
        when(usersRepository.existsByUserName("Sokra1212")).thenReturn(true);
        when(usersRepository.existsByUserName("Konto12")).thenReturn(false);
        when(usersRepository.findUserByUserName("Sokra1212")).thenReturn(user);

        usersService.changeName("Sokra1212", "Konto12");
        assertEquals("Konto12", user.getUserName());
    }

    @Test(expected = Exception.class)
    public void IfUserNameExistButNewUserNameExistToo() throws Exception {
        User user = new User();
        User user1 = new User();

        user1.setUserName("Marko19");
        user1.setPassword("pass1");

        user.setPassword("pass");
        user.setUserName("Sokra1919");
        when(usersRepository.existsByUserName("Sokra1919")).thenReturn(true);
        when(usersRepository.existsByUserName("Marko19")).thenReturn(true);
        when(usersRepository.findUserByUserName("Sokra1919")).thenReturn(user);

        usersService.changeName("Sokra1919", "Marko19");
        assertEquals("Marko19", user.getUserName());
    }

    @Test(expected = Exception.class)
    public void IfUserNameNotExistInPasswordChange() throws Exception {
        User user = new User();

        user.setPassword("password");
        user.setUserName("Sokratis");
        when(usersRepository.existsByUserName("Sokra")).thenReturn(false);

        usersService.changePassword("Sokra", "Fragkos");
    }

    @Test
    public void IfUserNameExistGoChangePassword() throws Exception {
        User user = new User();
        user.setPassword("Nothing");
        user.setUserName("Sokra");

        when(usersRepository.existsByUserName("Sokra")).thenReturn(true);
        when(usersRepository.findUserByUserName("Sokra")).thenReturn(user);

        usersService.changePassword("Sokra", "newPassword");
        assertEquals("newPassword", user.getPassword());
    }

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
    @Test(expected = Exception.class)
    public void getUserScoreWhileUserDoesntExistsShouldCreateException() throws Exception{
        when(usersRepository.existsByUserName("Giwrgos")).thenReturn(false);
        usersService.getUserScore("Giwrgos");
        fail("User Doesnt Exists");
    }
    @Test
    public void getUserScoreWhileUserDoesExistsShouldReturnScore() throws Exception{

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("Athina");
        user.setScore(50);
        users.add(user);
        when(usersRepository.existsByUserName("Athina")).thenReturn(true);
        when(usersRepository.findAll().stream().filter(user1 -> user1.getUserName().equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(users);
        int actualResult = usersService.getUserScore(user.getUserName());
        assertEquals(50,actualResult);
    }

    @Test(expected = Exception.class)
    public void getUserScoreWhileUserIsNullShouldCreateException() throws Exception{
        when(usersRepository.existsByUserName(null)).thenReturn(false);
        usersService.getUserScore(null);
        fail("User Doesnt Exists");
    }

    @Test(expected = Exception.class)
    public void deleteUserWhenIdDoesntExistsShouldReturnException() throws Exception{
        when(usersRepository.existsById("fdsgfdasgasd")).thenReturn(false);
        usersService.deleteUser("fdsgfdasgasd");
        fail("User DoesntExists");
    }
    @Test
    public void deleteUserWhenIdDoesExistsShouldDeleteTheUser() throws Exception{
        User user = new User();
        user.setId("foo");
        user.setUserName("Giorgos");
        when(usersRepository.existsById("foo")).thenReturn(true);
        usersService.deleteUser("foo");
    }
    @Test
    public void updateUserShouldUpdateTheUser() throws Exception{
        User user = new User();
        user.setId("foo");
        user.setUserName("Giorgos");
        usersService.updateUser(user);
    }

    @Test(expected = Exception.class)
    public void getUserWhenIdDoesntExistsShouldReturnException() throws Exception{
        when(usersRepository.existsByUserName("noo")).thenReturn(false);
        usersService.getUser("noo");
        fail("This should return User does not exist");
    }

    @Test
    public void getUserWhenIdDoesExistsShouldReturnUser() throws Exception{
        User user = new User();
        user.setId("foo");
        when(usersRepository.existsById("foo")).thenReturn(true);
        usersService.getUser("foo");
    }

    @Test
    public void addUserScoreWhenUserExistsByUserNameShouldAddUsersScore() throws Exception{
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("Athina");
        user.setScore(50);
        users.add(user);
        when(usersRepository.existsByUserName("Athina")).thenReturn(true);
        when(usersRepository.findAll().stream().filter(user1 -> user1.getUserName().equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(users);
        int actualResult = usersService.addScore(user.getUserName(),10);
        assertEquals(1,actualResult);
    }

    @Test(expected = Exception.class)
    public void addUserScoreWhenIdDoesntExistsShouldReturnException() throws Exception{
        when(usersRepository.existsByUserName("noo")).thenReturn(false);
        usersService.addScore("noo",5);
        fail("This should return User does not exist");
    }
    @Test(expected = Exception.class)
    public void addUserScoreWhileUserIsNullShouldCreateException() throws Exception{
        when(usersRepository.existsByUserName(null)).thenReturn(false);
        usersService.addScore(null,5);
        fail("User Doesnt Exists");
    }

    @Test
    public void IWantToReturnAllUsers() throws Exception {
        List<User> users = new ArrayList<>() ;
        User user = new User();
        user.setUserName("Sokra");
        user.setPassword("Fragkos");
        users.add(user);

        when(usersRepository.findAll()).thenReturn(users);
        assertEquals(users.get(0),usersService.getAllUser().get(0));
    }

    @Test(expected = Exception.class)
    public void RegisterUserThrowExceptionIfUserNameExist() throws Exception {
        User user = new User();
        user.setUserName("Example");

        when(usersRepository.existsByUserName(user.getUserName())).thenReturn(true);
        assertEquals(true,usersService.registerUser(user));
    }

    @Test
    public void RegisterUserWhenUserNameDoesNotExistThenSaveAndReturn() throws Exception {
        User user = new User();
        user.setUserName("Example");

        when(usersRepository.existsByUserName("Sokra")).thenReturn(false);
        assertEquals(true,usersService.registerUser(user));
    }


    @Test
    public void buyLifeWhenUserExistsByUserNameAndUserLivesAreEqualToOneAndUserPointsAreTwentyOrMoreShouldReturnTrue() throws Exception{
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("Konto41");
        user.setUserLives(1);
        user.setScore(25);
        users.add(user);
        when(usersRepository.existsByUserName("Konto41")).thenReturn(true);
        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName()
                        .equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(users);
        Boolean actualResult = usersService.buyLife(user.getUserName());
        assertEquals(true,actualResult);
    }

    @Test
    public void buyLifeWhenUserExistsByUserNameAndUserLivesAreMoreOrLessThanOneAndUserPointsAreLessThanTwentyShouldReturnFalse() throws Exception{
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("Konto41");
        user.setUserLives(3);
        user.setScore(10);
        users.add(user);
        when(usersRepository.existsByUserName("Konto41")).thenReturn(true);
        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName()
                        .equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(users);
        Boolean actualResult = usersService.buyLife(user.getUserName());
        assertEquals(false,actualResult);
    }
    @Test(expected = Exception.class)
    public void buyLifeIfUserDoesNotExistsByUserNameShouldReturnException() throws Exception{

        when(usersRepository.existsByUserName("foo")).thenReturn(false);
        usersService.buyLife("foo");
        fail("This should return wrong id");
    }
    @Test(expected = Exception.class)
    public void buyLifeIfUserIsNullShouldReturnException() throws Exception{

        when(usersRepository.existsByUserName(null)).thenReturn(false);
        usersService.buyLife(null);
        fail("This should return wrong id");
    }
    @Test(expected = Exception.class)
    public void RestartScoreAndLivesIfUserDoesNotExistByUserName() throws Exception{

        when(usersRepository.existsByUserName("foo")).thenReturn(false);
        usersService.restartScoreAndLives("foo");
        fail("User does not exist");
    }

    @Test(expected = Exception.class)
    public void BoughtAnswerIfUserDoesNotExistByUserName() throws Exception{

        when(usersRepository.existsByUserName("foo")).thenReturn(false);
        usersService.boughtAnswer("foo","Test");
        fail("User does not exist");
    }

    @Test(expected = Exception.class)
    public void boughtAnswerWithWrongUserNameShouldThrowException() throws Exception {
        when(usersRepository.existsByUserName("foo")).thenReturn(false);
        usersService.boughtAnswer("foo","what colour is the sky?");
        fail("User does not exist");
    }

    @Test(expected = Exception.class)
    public void boughtAnswerWithWrongQuestionShouldThrowException() throws Exception {
        User user = new User("1","foo",0,"123",3, Status.Away,UserState.PLAYING, UserRole.Player);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Question question = new Question("1","what colour is the sky?","blue",5);
        List<Question> questList = new ArrayList<>();
        questList.add(question);
        when(usersRepository.existsByUserName("foo")).thenReturn(true);
        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName()
                        .equals("foo"))
                .collect(Collectors.toList())).thenReturn(userList);
        when(questionsRepository.existsByQuestion("what colour is the sky?")).thenReturn(false);
        usersService.boughtAnswer("foo","what");
        fail("Question does not exist");
    }

    @Test
    public void boughtAnswerWithRightUserNameAndRightQuestionShouldReturnTheAnswer() throws Exception {
        User user = new User("1","foo",0,"123",3, Status.Away,UserState.PLAYING, UserRole.Player);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Question question = new Question("1","what colour is the sky?","blue",5);
        List<Question> questList = new ArrayList<>();
        questList.add(question);
        when(usersRepository.existsByUserName("foo")).thenReturn(true);
        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName()
                        .equals("foo"))
                .collect(Collectors.toList())).thenReturn(userList);
        when(questionsRepository.existsByQuestion("what colour is the sky?")).thenReturn(true);
        when( questionsRepository.findAll().stream()
                .filter(question1 -> question1.getQuestion()
                        .equals("what colour is the sky?"))
                .collect(Collectors.toList())).thenReturn(questList);
        String actualResult = usersService.boughtAnswer("foo","what colour is the sky?");

        assertEquals("blue",actualResult);
    }


    @Test(expected = Exception.class)
    public void setUserStateIfUserNameDoesNotExistThrowException() throws Exception{
        when(usersRepository.existsByUserName("foo")).thenReturn(false);
        usersService.setUserState("foo","sokra");
        fail("User does not exist");
    }
}