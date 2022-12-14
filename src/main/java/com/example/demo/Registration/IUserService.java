package com.example.demo.Registration;
import java.util.Optional;
public interface IUserService<ID, USER> {

    Boolean registerUser(USER user) throws Exception;

    Optional<USER> getUser(String userName) throws Exception;

    USER updateUser(USER user) throws Exception;

    void deleteUser(String userName) throws Exception;

    int addScore(String userName,int score) throws Exception;

    int getUserScore(String userName) throws Exception;

    int getUserLives(String userName) throws Exception;

    int setUserLives(String userName , int userLives) throws Exception;

    String changeName(String userName , String newName) throws Exception;

    String changePassword(String userName , String newPass) throws Exception;

    Boolean restartScoreAndLives(String userName) throws Exception;

    Boolean loginConfirmation(String username, String password) throws  Exception;

    Boolean setUserState(String userName,String locationTitle) throws  Exception;

    String checkUserState();

    String boughtAnswer(String userName, String question) throws Exception;

    Boolean buyLife(String userName) throws Exception;
}
