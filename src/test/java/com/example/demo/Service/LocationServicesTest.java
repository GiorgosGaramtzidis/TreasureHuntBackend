package com.example.demo.Service;
import static org.junit.Assert.*;
import com.example.demo.dao.LocationsRepositoryNew;
import com.example.demo.model.LocationsNew;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest

public class LocationServicesTest {
    @MockBean
    LocationsRepositoryNew locationsRepositoryNew;

    @Autowired
    LocationServices locationServices;

    @Test(expected = Exception.class)
     public void WhenStartLocationDoesNotExistShouldCreateExceptioon() throws Exception {
        when((locationsRepositoryNew.getStartLocation().getTitle()) == "Start")
                .thenReturn(true);
        locationServices.getStartLocation().getTitle();
        fail("this should take Start ");
    }


    @Test(expected = Exception.class)
    public void Test1()throws Exception {

        when(locationsRepositoryNew.getStartLocation()).thenReturn(null);
        locationServices.getStartLocation();
    }

    @Test
    public void test2() throws Exception{
        when(locationsRepositoryNew.getStartLocation()).thenReturn(new LocationsNew("Start","idk"));
        String location = locationServices.getStartLocation().getTitle();
        assertEquals("Start",location);
    }

    @Test
    public void WhenITakeLocationTitleIWantToSeeWhereIsNextLocation()throws Exception{

        when(locationsRepositoryNew.getNextLocation("Kafeteria")).thenReturn(new LocationsNew("Kafeteria","Grammatia"));
        String location = locationServices.getNextLocation("Kafeteria").getNextLocation();
        assertEquals("Grammatia", location);
    }
    @Test(expected = Exception.class)
    public void WhenNextLocationIsNullThrowException() throws Exception{
        when(locationsRepositoryNew.getNextLocation(null)).thenReturn(null);
        locationServices.getNextLocation(null);

    }

    @Test(expected = Exception.class)
    public void WhenIGetNextLocationWithSpacesThrowException() throws Exception{
        when(locationsRepositoryNew.getNextLocation(" ")).thenReturn(null);
        locationServices.getNextLocation(" ");
    }

    @Test
    public void oka() throws Exception{
      //  String ok = locationsRepositoryNew.getStartLocation().
    }
}