package com.example.demo.api;

import com.example.demo.model.LocationsNew;
import com.example.demo.model.Question;
import org.bson.types.ObjectId;
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
public class LocationsControllerNewTest {

    @Autowired
    LocationsControllerNew locationsControllerNew;

    @Test
    public void getStartLocationWhenTitleStartExistInLocationCollection() throws Exception {
        String startLocation = new LocationsNew(new ObjectId("5fba6528e27b5ebc0a7e5a26"),41.076797,23.553648,"Start",new Question("Όλους ταΐζει και δεν τρώει. Τι είναι;","Κουτάλι",5),"Kafeteria").toString();
        assertEquals(new ResponseEntity<>(startLocation, HttpStatus.OK).toString(),locationsControllerNew.StartLocation().toString());
    }

    @Test(expected = Exception.class)
    public void getStartLocationWhenTitleStartNotExistInLocationCollection() throws Exception {
        LocationsNew startLocation = new LocationsNew(new ObjectId("5fba6528e27b5ebc0a7e5a26"),41.076797,23.553648,null,new Question("Όλους ταΐζει και δεν τρώει. Τι είναι;","Κουτάλι",5),"Kafeteria");
        assertEquals(new ResponseEntity<>(startLocation, HttpStatus.OK),locationsControllerNew.StartLocation());
    }
}