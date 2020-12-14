package com.example.demo.Service;

import com.example.demo.dao.LocationsRepositoryNew;
import com.example.demo.model.LocationsNew;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServicesTest {
    @MockBean
    LocationsRepositoryNew locationsRepositoryNew;

    @Autowired
    LocationServices locationServices;

    @org.junit.Test
    public void registerNewLocationIfLocationIdIsAvailableShouldSaveTheLocation() throws Exception {
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setV(42.44);
        locationsNew.setV1(32.65);
        locationsNew.setNextLocation("Start");
        when(locationsRepositoryNew.existsById(new ObjectId("5fba6528e27b5ebc0a7e5a26"))).thenReturn(true);
        assertEquals(locationsNew,locationServices.registerNewLocation(locationsNew));
    }
    @org.junit.Test(expected = Exception.class)
    public void registerNewLocationIfLocationIdIsNotAvailableShouldCreateException() throws Exception {
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setId(new ObjectId("5fba658ea01cf8e17f34255c"));
        locationsNew.setV(42.44);
        locationsNew.setV1(32.65);
        locationsNew.setNextLocation("Start");
        when(locationsRepositoryNew.existsById(new ObjectId("5fba658ea01cf8e17f34255c"))).thenReturn(true);
        assertEquals(locationsNew,locationServices.registerNewLocation(locationsNew));
    }
    @org.junit.Test(expected = Exception.class)
    public void deleteNewLocationIfLocationIdDoesNotExistsShouldCreateException() throws Exception {
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setId(new ObjectId("5fba658ea01cf8e17f34255c"));
        locationsNew.setV(42.44);
        locationsNew.setV1(32.65);
        locationsNew.setNextLocation("Start");
        when(locationsRepositoryNew.existsById(new ObjectId("5fba658ea01cf8e17f34255c"))).thenReturn(true);
        locationServices.deleteLocation(new ObjectId("5fba6528e27b5ebc0a7e5a26"));
    }
    @org.junit.Test
    public void deleteNewLocationIfLocationIdDExistsShouldDeleteTheLocation() throws Exception {
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setId(new ObjectId("5fba658ea01cf8e17f34255c"));
        locationsNew.setV(42.44);
        locationsNew.setV1(32.65);
        locationsNew.setNextLocation("Start");
        when(locationsRepositoryNew.existsById(new ObjectId("5fba658ea01cf8e17f34255c"))).thenReturn(true);
        locationServices.deleteLocation(new ObjectId("5fba658ea01cf8e17f34255c"));
    }
    @org.junit.Test(expected = Exception.class)
    public void deleteNewLocationIfLocationIdDisNullShouldCreateException() throws Exception {
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setId(new ObjectId("5fba658ea01cf8e17f34255c"));
        locationsNew.setV(42.44);
        locationsNew.setV1(32.65);
        locationsNew.setNextLocation("Start");
        when(locationsRepositoryNew.existsById(new ObjectId("5fba658ea01cf8e17f34255c"))).thenReturn(true);
        locationServices.deleteLocation(null);
    }

    @org.junit.Test(expected = Exception.class)
    public void updateNewLocationIfLocationIdDoesNotExistsShouldCreateException() throws Exception {
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setId(new ObjectId("5fba658ea01cf8e17f34255c"));
        locationsNew.setV(42.44);
        locationsNew.setV1(32.65);
        locationsNew.setNextLocation("Start");
        when(locationsRepositoryNew.existsById(new ObjectId("5fba658ea01cf8e17f34255c"))).thenReturn(true);
        assertEquals(locationsNew,locationServices.updateLocation(new ObjectId("5fba6528e27b5ebc0a7e5a26"),locationsNew));
    }
    @org.junit.Test
    public void updateNewLocationIfLocationIdIsAvailableShouldSaveTheLocation() throws Exception {
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setV(42.44);
        locationsNew.setV1(32.65);
        locationsNew.setNextLocation("Start");
        when(locationsRepositoryNew.existsById(new ObjectId("5fba6528e27b5ebc0a7e5a26"))).thenReturn(true);
        assertEquals(locationsNew,locationServices.updateLocation(new ObjectId("5fba6528e27b5ebc0a7e5a26"),locationsNew));
    }
    @Test(expected = Exception.class)
    public void updateNewLocationIfLocationIdDisNullShouldCreateException() throws Exception {
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setId(new ObjectId("5fba658ea01cf8e17f34255c"));
        locationsNew.setV(42.44);
        locationsNew.setV1(32.65);
        locationsNew.setNextLocation("Start");
        when(locationsRepositoryNew.existsById(new ObjectId("5fba658ea01cf8e17f34255c"))).thenReturn(true);
        assertEquals(locationsNew,locationServices.updateLocation(null,locationsNew));
    }
}