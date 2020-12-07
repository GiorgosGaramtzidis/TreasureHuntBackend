package com.example.demo;

import com.example.demo.Service.LocationServices;
import com.example.demo.api.LocationsControllerNew;

import com.example.demo.model.LocationsNew;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
class TreasureHuntApplicationTests {

	@Autowired
	private LocationsControllerNew locationsControllerNew;

	@Test
	void contextLoads() throws Exception{
		assertThat(locationsControllerNew).isNotNull();
	}


}
