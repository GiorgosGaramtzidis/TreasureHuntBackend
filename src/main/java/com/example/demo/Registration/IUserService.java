package com.example.demo.Registration;
import java.util.Optional;
public interface IUserService<ID, USER> {

    Boolean registerUser(USER user) throws Exception;

    Optional<USER> getUser(String userName) throws Exception;

    USER updateUser(USER user) throws Exception;

    void deleteUser(String userName) throws Exception;

    int addScore(String userName,int score) throws Exception;

    int getUserScore(String userName) throws Exception;

    Boolean loginConfirmation(String username, String password) throws  Exception;

}
