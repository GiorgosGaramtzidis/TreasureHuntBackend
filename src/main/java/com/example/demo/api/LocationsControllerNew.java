package com.example.demo.api;


import com.example.demo.Service.LocationServices;
import com.example.demo.model.LocationsNew;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/Locations")
@RestController
public class LocationsControllerNew {

    @Autowired
    private LocationServices locationServices;

    @GetMapping("/getAllLocations")
    ResponseEntity getAllLocations() throws Exception{
        return new ResponseEntity<>(locationServices.getAllLocations(),HttpStatus.OK);
    }

    @PostMapping("/addLocation")
    ResponseEntity addLocation(@RequestBody LocationsNew locationsNew) throws Exception {
        locationsNew.setId(new ObjectId());
        return new ResponseEntity<>(locationServices.registerNewLocation(locationsNew),HttpStatus.OK);
    }

    @PutMapping("/updateLocation")
    ResponseEntity updateLocation(@RequestParam ObjectId id,@RequestBody LocationsNew locationsNew) throws Exception{
        locationsNew.setId(id);
        return new ResponseEntity<>(locationServices.updateLocation(id,locationsNew),HttpStatus.OK);

    }

    @GetMapping("/Start")
    ResponseEntity StartLocation() throws Exception {
        return new ResponseEntity<>(locationServices.getStartLocation(),HttpStatus.OK);
    }

    @PostMapping("/Next")
    ResponseEntity nextLocation(@RequestParam("nextLocation") String nextLocation) throws Exception {
        return new ResponseEntity<>(locationServices.getNextLocation(nextLocation),HttpStatus.OK);
    }
}
