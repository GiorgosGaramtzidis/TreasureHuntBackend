package com.example.demo.Service;

import com.example.demo.Registration.IUserService;
import com.example.demo.dao.QuestionsRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.Question;
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

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public Boolean registerUser(User user) throws Exception {
        if (usersRepository.existsByUserName(user.getUserName())) {
            throw new Exception("User with this username : " + user.getUserName() + " already exists");
        }
        usersRepository.save(user);
        return true;
    }
    @Override
    public Optional<User> getUser(String userId) throws Exception {
        if (!usersRepository.existsById(userId))
            throw new Exception("User Does not exists");
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
        if (usersRepository.existsById(userId)) {
            usersRepository.deleteById(userId);
            return;
        }
        throw new Exception("user id is null");
    }

    @Override
    public int addScore(String userName, int score) throws Exception {
        if (usersRepository.existsByUserName(userName)) {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            user.get(0).setScore(user.get(0).getScore() + score);
            usersRepository.save(user.get(0));
            return 1;
        }
        throw new Exception("User does not exist");
    }
    @Override
    public String changeName(String userName , String newName) throws Exception {
        if(usersRepository.existsByUserName(userName))
        {
            if (usersRepository.existsByUserName(newName))
            {
                throw new Exception("User exist with this userName");
            }
        User user = usersRepository.findUserByUserName(userName);
        user.setUserName(newName);
        usersRepository.save(user);
        return newName;
        }
        throw new Exception("User does not exist");

    }

    @Override
    public String changePassword(String userName , String newPass) throws Exception{
        if(usersRepository.existsByUserName(userName)){
            User user = usersRepository.findUserByUserName(userName);
            user.setPassword(newPass);
            usersRepository.save(user);
            return newPass;
        }
        throw new Exception("User does not exist");

    }
        /*
        if(usersRepository.existsByUserName(userName)){
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            user.get(0).setPassword(newPass);
            usersRepository.save(user.get(0));
            return newPass;
        }
        throw new Exception("UserName does not exist");
    }*/

    @Override
    public Boolean setUserState(String userName, String locationTitle) throws Exception {
        if (usersRepository.existsByUserName(userName)) {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            if (locationTitle.equals("end")) {
                user.get(0).setUserState(UserState.WIN);
                usersRepository.save(user.get(0));
                return true;
            }
            return false;
        }
        throw new Exception("User does not exist");
    }

    @Override
    public String checkUserState() {
        List<User> user = usersRepository.findAll().stream()
                .filter(user1 -> user1.getUserState()
                        .equals(UserState.WIN))
                .collect(Collectors.toList());
        if ((user.size()) == 0)
            return UserState.PLAYING.toString();
        else
            return user.get(0).getUserName();
    }

    @Override
    public int getUserScore(String userName) throws Exception {
        if (usersRepository.existsByUserName(userName)) {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            return user.get(0).getScore();
        }
        throw new Exception("User does not exist");
    }

    @Override
    public Boolean restartScoreAndLives(String userName) throws Exception {
        if (usersRepository.existsByUserName(userName)) {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            user.get(0).setScore(0);
            user.get(0).setUserLives(5);
            user.get(0).setUserState(UserState.PLAYING);
            usersRepository.save(user.get(0));
            return true;
        }
        throw new Exception("User does not exist");
    }

    @Override
    public Boolean loginConfirmation(String username, String password) throws Exception {
        if (usersRepository.existsByUserName(username)) {
            String UsersPassword = usersRepository.findUserByUserName(username).getPassword();
            if (UsersPassword.equals(password))
                return true;
            return false;
        }
        throw new Exception("UserName doesn't Exists");
    }

    @Override
    public String boughtAnswer(String userName, String question) throws Exception {
        if (usersRepository.existsByUserName(userName)) {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            user.get(0).setScore(user.get(0).getScore() - 10);

            usersRepository.save(user.get(0));

            if(questionsRepository.existsByQuestion(question)){
                List<Question> questions = questionsRepository.findAll().stream()
                        .filter(question1 -> question1.getQuestion()
                                .equals(question))
                        .collect(Collectors.toList());
                return questions.get(0).getAnswer();
            }
            throw new Exception("Question does not exist");
        }
        throw new Exception("User does not exist");
    }
    @Override
    public Boolean buyLife(String userName) throws Exception {
        if(usersRepository.existsByUserName(userName)){
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());

            if(user.get(0).getUserLives()==1 && user.get(0).getScore()>=20){
                user.get(0).setUserLives(user.get(0).getUserLives()+1);
                user.get(0).setScore(user.get(0).getScore()-10);
                usersRepository.save(user.get(0));
                return true;
            }
            else{
                return false;
            }
        }
        else
            throw new Exception("Wrong id");
    }
    @Override
    public int getUserLives(String userName) throws Exception{
        if (usersRepository.existsByUserName(userName)) {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            return user.get(0).getUserLives();
        }
        throw new Exception("User does not exist");
    }
    @Override
    public int setUserLives(String userName, int userLives) throws Exception {
        if (usersRepository.existsByUserName(userName)) {
            List<User> user = usersRepository.findAll().stream()
                    .filter(user1 -> user1.getUserName()
                            .equals(userName))
                    .collect(Collectors.toList());
            user.get(0).setUserLives(userLives);
            usersRepository.save(user.get(0));
            return 1;
        }
        throw new Exception("User does not exist");
    }
}
