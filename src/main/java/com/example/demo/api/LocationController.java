package com.example.demo.api;

import com.example.demo.dao.LocationRepository;
import com.example.demo.model.Locations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/Locations")
@RestController
public class LocationController {

    @Autowired
    public LocationRepository locationsRepository;

    @GetMapping(value = "/find")
    public List<Locations> getAllLocations(){
        return locationsRepository.findAll();
    }

    @PostMapping(value = "/add")
    public String saveLocation(@RequestBody Locations locations){
        locationsRepository.save(locations);
        return "Added question";
    }

}
