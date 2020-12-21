package com.example.demo.Service;
import static org.junit.Assert.*;
import com.example.demo.dao.LocationsRepositoryNew;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.LocationsNew;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServicesTest {
    @MockBean
    LocationsRepositoryNew locationsRepositoryNew;

    @Autowired
    LocationServices locationServices;



    @Test
    public void WhenITakeLocationTitleIWantToSeeWhereIsNextLocation()throws Exception{

        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setTitle("Kafeteria");
        locationsNew.setNextLocation("Grammatia");
        when(locationsRepositoryNew.getNextLocation(locationsNew.getTitle())).thenReturn(locationsNew);
        String location = locationServices.getNextLocation(locationsNew.getTitle()).getNextLocation();
        assertEquals("Grammatia", location);
    }
    @Test(expected = Exception.class)
    public void WhenITakeLocationTitleAndNextLocationIsNull() throws Exception {

        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setTitle("Start");
        locationsNew.setNextLocation(null);
        when(locationsRepositoryNew.getStartLocation().getTitle()).thenReturn(locationsNew.getTitle());
        when(locationsRepositoryNew.getNextLocation(locationsNew.getTitle())).thenReturn(locationsNew);
        assertEquals(null, locationsNew.getNextLocation());
    }

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

    @Test(expected = Exception.class)
    public void WhenIGetNextLocationWithSpacesThrowException() throws Exception {
        when(locationsRepositoryNew.getNextLocation(" ")).thenReturn(null);
        locationServices.getNextLocation(" ");
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


    @Test(expected = Exception.class)
    public void GetAllLocationThrowExceptionIfNotExist() throws Exception{
        List<LocationsNew> locationsNewList = new ArrayList<LocationsNew>();
        when(locationsRepositoryNew.existsById(new ObjectId("5fba658ea01cf8e17f34255c"))).thenReturn(true);
        assertEquals(locationsNewList.isEmpty(),locationServices.getAllLocations());
    }

    @Test
    public void GetAllLocationIfExist() throws Exception{
        List<LocationsNew> locationsNewList = new ArrayList<LocationsNew>();
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setTitle("Sokra");
        locationsNew.setNextLocation("Kafeteria");
        locationsNew.setV(1234);
        locationsNew.setV1(1234);
        locationsNewList.add(locationsNew);
        when(locationsRepositoryNew.findAll()).thenReturn(locationsNewList);
        assertEquals(locationsNewList,locationServices.getAllLocations());
    }

    @Test
    public void WhenStartLocationExistThenReturnThisLocation() throws Exception {
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setTitle("Start");
        when(locationsRepositoryNew.getStartLocation()).thenReturn(locationsNew);
        assertEquals(locationsNew.getTitle(),locationServices.getStartLocation().getTitle());
    }

    @Test(expected = Exception.class)
    public void WhenStartLocationNotExistThenThrowException() throws Exception{
        LocationsNew locationsNew = new LocationsNew();
        locationsNew.setId(new ObjectId("5fba658ea01cf8e17f34255c"));
        locationsNew.setV(42.44);
        locationsNew.setV1(32.65);
        locationsNew.setNextLocation("Start");
        when(locationsRepositoryNew.existsById(new ObjectId("5fba658ea01cf8e17f34255c"))).thenReturn(true);
        assertEquals(locationsNew.getTitle(),locationServices.getStartLocation().getTitle());
    }

}