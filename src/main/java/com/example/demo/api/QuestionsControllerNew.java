package com.example.demo.api;


import com.example.demo.model.QuestionsNew;
import com.example.demo.Service.QuestionsServices;
import com.example.demo.model.Questions;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api")
@RestController
public class QuestionsControllerNew
{
    @Autowired
    private QuestionsServices questionsServices;


    @PostMapping(value = "/addQuestions")
    public ResponseEntity addQuestion(@RequestBody QuestionsNew questionsNew)throws Exception{
        HashMap<String, Object> resp = new HashMap<>();
        if (questionsServices.addQuestions(questionsNew)){
            resp.put("question",questionsNew);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        resp.put("Question exist",questionsNew.getId());
        return new ResponseEntity<>(resp, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @GetMapping("/getQuestion/{id}")
      public ResponseEntity getQuestion(@PathVariable String id) throws Exception{
        return new ResponseEntity<>(questionsServices.getQuestions(id),HttpStatus.OK);

    }

    @GetMapping("/getAllQuestions" )
    public ResponseEntity getAllQuestions() throws Exception {
        return new ResponseEntity<>(questionsServices.getAllQuestions(), HttpStatus.OK);
    }






    @PutMapping(path = "/updateQuestion/{id}")
    public ResponseEntity updateQuestions(@PathVariable String id,@RequestBody QuestionsNew questions) throws Exception {
        return new ResponseEntity<>(questionsServices.updateQuestions(id,questions), HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteQuestion(@PathVariable ("id")String id)throws Exception{
    questionsServices.deleteQuestion(id);
        HashMap<String,String> resp =new HashMap<>();
    resp.put("Message","Question is successfully deleted");
    return new ResponseEntity<>(resp, HttpStatus.OK);
    }



}
