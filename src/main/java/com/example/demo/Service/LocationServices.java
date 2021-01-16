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
        if (locationsRepositoryNew.existsById(locationsNew.getId()))
            throw new Exception("Its Not Available" + locationsNew.getId());
        locationsRepositoryNew.save(locationsNew);
        return locationsNew;
    }


    @Override
    public List<LocationsNew> getAllLocations() throws Exception {
        if (locationsRepositoryNew.findAll().isEmpty())
            throw new Exception("Locations collection is empty");
        return locationsRepositoryNew.findAll();
    }

    @Override
    public LocationsNew updateLocation(ObjectId id ,LocationsNew location) throws Exception {
        if (locationsRepositoryNew.existsById(location.getId()))
            throw new Exception("Id doesn't exist" + location.getId());
        locationsRepositoryNew.save(location);
        return location;
    }

    @Override
    public void deleteLocation(ObjectId LocationId) throws Exception {
        if (locationsRepositoryNew.existsById(LocationId)) {
            locationsRepositoryNew.deleteById(LocationId);
            return;
        }
        throw new Exception("location id is wrong");
    }

    public LocationsNew getStartLocation() throws Exception {
        if (locationsRepositoryNew.getStartLocation()==null){
            throw new Exception("Start Locations doesn't exist");
        }
        return locationsRepositoryNew.getStartLocation();
    }

    public LocationsNew getCasinoLocation() throws Exception{
        if (locationsRepositoryNew.getCasinoLocation()==null){
            throw new Exception("Casino Location doesn't exist");
        }
        return locationsRepositoryNew.getCasinoLocation();
    }

    public LocationsNew getWatchTowerLocation() throws Exception{
        if (locationsRepositoryNew.getWatchTowerLocation()==null){
            throw new Exception("Watch Tower doesn't exist");
        }
        return locationsRepositoryNew.getWatchTowerLocation();
    }

    public LocationsNew getNextLocation(String nextLocation) throws Exception{

        if (locationsRepositoryNew.getNextLocation(nextLocation) == null){
            throw new Exception("The location you ask does not exist");
        }
        return locationsRepositoryNew.getNextLocation(nextLocation);
    }
}
