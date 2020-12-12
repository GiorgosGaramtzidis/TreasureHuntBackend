package com.example.demo.Service;

import com.example.demo.Registration.IUserService;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.User;
import com.example.demo.model.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersService implements IUserService<UUID, User> {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Boolean registerUser(User user) throws Exception {
        if (usersRepository.existsByUserName(user.getUserName())) {
            throw new Exception("User with this username : "+user.getUserName()+" already exists");
        }
        usersRepository.save(user);
        return true;
    }

    @Override
    public Optional<User> getUser(String userId) throws Exception {
        return usersRepository.findById(userId);
    }

    public List<User> getAllUser() throws Exception {
        List<User> user = usersRepository.findAll();
        return user;
    }

    @Override
    public User updateUser(User user) throws Exception {
        user = usersRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        if (userId == null) {
            throw new Exception("user id is null");
        } else {
            usersRepository.deleteById(userId);
        }
    }


    @Override
    public int addScore(String userName,int score) throws Exception{
        if(usersRepository.existsByUserName(userName)) {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            user.get(0).setScore(user.get(0).getScore()+score);
            usersRepository.save(user.get(0));
            return 1;
        }
        throw new Exception("User does not exist");
    }

    @Override
    public Boolean setUserState(String userName,String locationTitle) throws Exception {
        if (usersRepository.existsByUserName(userName)) {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            if (locationTitle.equals("end")) {
                user.get(0).setUserState(UserState.WIN);
                usersRepository.save(user.get(0));
            }
            return true;
        }
        throw new Exception("User does not exist");
    }

    @Override
    public String checkUserState() {
        List<User> user = usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserState()
                        .equals(UserState.WIN))
                .collect(Collectors.toList());
        if ((user.size())==0)
            return UserState.PLAYING.toString();
        else
            return user.get(0).getUserName();
    }
    @Override
    public int getUserScore(String userName) throws Exception{
        if(usersRepository.existsByUserName(userName)) {
              List<User> user = usersRepository.findAll().stream()
                      .filter(user1 -> user1.getUserName()
                              .equals(userName))
                      .collect(Collectors.toList());
            return user.get(0).getScore();
        }
        throw new Exception("User does not exist");
    }

    @Override
    public Boolean restartScoreAndLives() throws Exception{
        List<User> userList = usersRepository.findAll();
        for(int i=0;i<userList.size();i++){
            userList.get(i).setScore(0);
            userList.get(i).setUserLives(5);
            usersRepository.save(userList.get(i));
        }
        return true;
    }

    @Override
    public  Boolean loginConfirmation(String username, String password) throws Exception{
        if (usersRepository.existsByUserName(username)) {
            String UsersPassword =usersRepository.findUserByUserName(username).getPassword();
            if (UsersPassword.equals(password))
                return true;
            return false;
        }
        //return "User not found";
        throw new Exception("UserName doesn't Exists");
    }

}
