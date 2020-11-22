package com.example.demo.api;


import com.example.demo.Service.MultipleChoiceQuetionServices;
import com.example.demo.model.LocationsNew;
import com.example.demo.model.MultipleChoiceQuestionsNew;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/MultipleChoice")
@RestController
public class MultipleChoiceQuestionsControllerNew {

    @Autowired
    MultipleChoiceQuetionServices multipleChoiceQuetionServices;


    @GetMapping("/getAllMultipleChoice")
    ResponseEntity getAllLocations() throws Exception{
        return new ResponseEntity<>(multipleChoiceQuetionServices.getAllMCQ(), HttpStatus.OK);
    }

    @PostMapping("/addMultipleChoice")
    ResponseEntity addLocation(@RequestBody MultipleChoiceQuestionsNew multipleChoiceQuestionsNew) throws Exception {
        multipleChoiceQuestionsNew.setId(new ObjectId());
        return new ResponseEntity<>(multipleChoiceQuetionServices.registerNewMCQ(multipleChoiceQuestionsNew),HttpStatus.OK);
    }
}
