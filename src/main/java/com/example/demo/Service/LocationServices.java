package com.example.demo.Service;

import com.example.demo.Registration.LocationsRegistration;
import com.example.demo.dao.LocationsRepositoryNew;
import com.example.demo.model.LocationsNew;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServices implements LocationsRegistration<ObjectId,LocationsNew> {

    @Autowired
    LocationsRepositoryNew locationsRepositoryNew;

    @Override
    public LocationsNew registerNewLocation(LocationsNew locationsNew) throws Exception {
        if (locationsRepositoryNew.existsById(locationsNew.getId())) {
            throw new Exception("Id dosen't exist" + locationsNew.getId());
        }else {
            return locationsRepositoryNew.save(locationsNew);
        }

    }


    @Override
    public List<LocationsNew> getAllLocations() throws Exception {
        if (locationsRepositoryNew.findAll().isEmpty()){
            throw new Exception("Locations collection is empty");
        } else {
            return locationsRepositoryNew.findAll();
        }
    }

    @Override
    public LocationsNew updateLocation(ObjectId id ,LocationsNew location) throws Exception {
        if (locationsRepositoryNew.existsById(location.getId())) {
            throw new Exception("Id dosen't exist" + location.getId());
        }else {
            return locationsRepositoryNew.save(location);
        }
    }

    @Override
    public void deleteLocation(ObjectId LocationId) throws Exception {
        if (LocationId == null) {
            throw new Exception("user id is null");
        } else {
            locationsRepositoryNew.deleteById(LocationId);
        }

    }
}
