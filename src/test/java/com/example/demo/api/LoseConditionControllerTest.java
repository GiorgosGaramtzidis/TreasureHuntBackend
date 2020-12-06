/*package com.example.demo.api;

import com.example.demo.model.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoseConditionControllerTest {

    @Autowired
    LoseConditionController loseConditionController;
    @Test
    public void updateUserLivesIfIdExists() throws Exception {
        Users user = new Users("7","Ath",200,null,5);
        assertEquals(new ResponseEntity<>(user, HttpStatus.OK),loseConditionController.updateUserLives(user));
    }
    @Test(expected = Exception.class)
    public void updateUserLivesIfIdDoesNotExists() throws Exception {
        Users user = new Users("9","John",200,"1012",5);
        assertEquals(new ResponseEntity<>(user, HttpStatus.OK),loseConditionController.updateUserLives(user));
    }
}

 */