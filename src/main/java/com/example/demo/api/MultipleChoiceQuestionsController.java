package com.example.demo.api;

import com.example.demo.dao.MultipleChoiceQuestionsRepository;
import com.example.demo.model.MultipleChoiceQuestions;
import com.example.demo.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/MultipleChoiceQuestions")
@RestController
public class MultipleChoiceQuestionsController {

    @Autowired
    public MultipleChoiceQuestionsRepository multipleChoiceQuestionsRepository;

    @GetMapping(value = "/find")
    public List<MultipleChoiceQuestions> getAllMultipleChoiceQuestions(){
        return multipleChoiceQuestionsRepository.findAll();
    }

    @GetMapping(value = "/find/{id}")
    public Optional<MultipleChoiceQuestions> getMultipleChoiceQuestionById(@PathVariable int id){

        return multipleChoiceQuestionsRepository.findById(id);
    }
    @GetMapping(value = "/find/points/{points}")
    public List<MultipleChoiceQuestions> getMultipleChoiceQuestionWithPoints(@PathVariable int points) {
        return multipleChoiceQuestionsRepository.getMultipleChoiceQuestionWithPoints(points);
    }

    @PostMapping(value = "/addMultipleChoiceQuestion")
    public String saveMultipleChoiceQuestion(@RequestBody MultipleChoiceQuestions multipleChoiceQuestions){
        multipleChoiceQuestionsRepository.save(multipleChoiceQuestions);
        return "Added multiple choice question";
    }

    @DeleteMapping(value = "/delete")
    public String deleteAllMultipleChoiceQuestions(){
        multipleChoiceQuestionsRepository.deleteAll();
        return "All multiple choice questions were deleted!!";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteMultipleChoiceQuestion(@PathVariable int id){
        multipleChoiceQuestionsRepository.deleteById(id);
        return "Multiple choice question was deleted!!";
    }


}
