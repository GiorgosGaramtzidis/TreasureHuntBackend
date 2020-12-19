package com.example.demo.Service;

import com.example.demo.dao.LocationsRepositoryNew;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.LocationsNew;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.model.UserState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

    @MockBean
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @MockBean
    LocationsRepositoryNew locationsRepository;

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

    @Test(expected = Exception.class)
    public void boughtAnswerWithWrongName() throws Exception {
        when(usersRepository.existsByUserName("noo")).thenReturn(false);
        usersService.boughtAnswer("noo","bibliothiki");
        fail("This should return User does not exist");
    }

   /* @Test
    public void boughtAnswerWithRightName() throws Exception {
        List<User> usersList = new ArrayList<>();
        User user = new User();
        user.setUserName("Thalia");
        usersList.add(user);
        List<LocationsNew> locationsList = new ArrayList<>();
        Question question = new Question("What","That",5);
        LocationsNew location = new LocationsNew();
        location.setTitle("Start");
        location.setQuestions(question);
        when(usersRepository.existsByUserName(user.getUserName())).thenReturn(true);

        when(usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserName()
                        .equals(user.getUserName()))
                .collect(Collectors.toList())).thenReturn(usersList);

        when(locationsRepository.findAll().stream()
                .filter(locationsNew -> locationsNew.getTitle()
                        .equals(location.getTitle()))
                .collect(Collectors.toList())).thenReturn(locationsList);
        String actualResult = usersService.boughtAnswer("Thalia","Start");
        assertEquals("What",actualResult);
    }*/
}