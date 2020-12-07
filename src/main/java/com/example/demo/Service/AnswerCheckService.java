package com.example.demo.Service;

import com.example.demo.Registration.AnswerCheck;
import com.example.demo.api.AnswerCheckController;
import com.example.demo.dao.LocationsRepositoryNew;

import com.example.demo.model.LocationsNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerCheckService implements AnswerCheck<String> {
    @Autowired
    private LocationsRepositoryNew locationsRepositoryNew;
    @Override
    public Boolean AnswerCheck(String usersAnswer,String locationTitle) throws Exception {

        if(locationsRepositoryNew.existsByTitle(locationTitle)) {
            List<LocationsNew> locationsNews = locationsRepositoryNew.findAll().stream().filter(locationsNew -> locationsNew.getTitle().equals(locationTitle))
                    .collect(Collectors.toList());
            if (usersAnswer.equals(locationsNews.get(0).getQuestions().getAnswer())) {
                System.out.println("correct title correct answer yikes");
                return true;
            } else {
                System.out.println("wrong answer correct title");
                return false;
            }
        }
        System.out.println("failed wrong Location title");
        throw new Exception("Location Does Not Exist");
    }


}

