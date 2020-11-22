package com.example.demo.Registration;
import java.util.Optional;
public interface UsersRegistration<ID, USER> {
    Boolean registerUser(USER user) throws Exception;

    Optional<USER> getUser(ID userId) throws Exception;

    USER updateUser(USER user) throws Exception;

    void deleteUser(ID userId) throws Exception;

}
