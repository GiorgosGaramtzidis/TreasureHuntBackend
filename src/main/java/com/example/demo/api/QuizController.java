package com.example.demo.api;

import com.example.demo.dao.QuizRepository;
import com.example.demo.model.Quiz;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/Quiz")
@RestController
public class QuizController
{
    @Autowired
    public QuizRepository quizRepository ;

    @GetMapping(value = "/all")
    public List<Quiz> getAllQuiz(){

        return quizRepository.findAll();
    }

    @GetMapping(value = "/find/{id}")
    public Optional<Quiz> getQuizById(@PathVariable int id){

        return quizRepository.findById(id);
    }

    @GetMapping(value = "/find/score/{score}")
    public List<Quiz> getQuizWithScore(@PathVariable int score) {
        return quizRepository.getQuestionWithPoints(score);
    }

    @PostMapping(value = "/addQuiz")
    public String saveQuiz(@RequestBody Quiz quiz){
        quizRepository.save(quiz);
        return "Added quiz";
    }

    @DeleteMapping(value = "/delete")
    public String deleteAllQuiz(){
        quizRepository.deleteAll();
        return "Delete All Users";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteQuiz(@PathVariable int id){
        quizRepository.deleteById(id);
        return "Bye User";
    }
}
