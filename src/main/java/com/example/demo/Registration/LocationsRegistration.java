package com.example.demo.Registration;

import com.example.demo.model.LocationsNew;

import java.util.List;

public interface LocationsRegistration<ID,LOCATION> {
    LOCATION registerNewLocation(LOCATION location) throws Exception;

    List<LocationsNew> getAllLocations() throws Exception;

    LOCATION updateLocation(ID id,LOCATION Location) throws Exception;

    void deleteLocation(ID LocationId) throws Exception;
}
