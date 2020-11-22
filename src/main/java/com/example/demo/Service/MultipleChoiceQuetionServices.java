package com.example.demo.Service;

import com.example.demo.Registration.MultipleChoiceQuestionRegistration;
import com.example.demo.dao.MultipleChoiceQuestionsRepositoryNew;
import com.example.demo.model.MultipleChoiceQuestionsNew;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultipleChoiceQuetionServices implements MultipleChoiceQuestionRegistration<ObjectId, MultipleChoiceQuestionsNew> {

    @Autowired
    MultipleChoiceQuestionsRepositoryNew multipleChoiceQuestionsRepositoryNew;

    @Override
    public MultipleChoiceQuestionsNew registerNewMCQ(MultipleChoiceQuestionsNew multipleChoiceQuestionsNew) throws Exception {
        if (multipleChoiceQuestionsRepositoryNew.existsById(multipleChoiceQuestionsNew.getId())) {
            throw new Exception("Id dosen't exist" + multipleChoiceQuestionsNew.getId());
        }else {
            return multipleChoiceQuestionsRepositoryNew.save(multipleChoiceQuestionsNew);
        }
    }

    @Override
    public List<MultipleChoiceQuestionsNew> getAllMCQ() throws Exception {
        if (multipleChoiceQuestionsRepositoryNew.findAll().isEmpty()){
            throw new Exception("Locations collection is empty");
        } else {
            return multipleChoiceQuestionsRepositoryNew.findAll();
        }
    }
}
