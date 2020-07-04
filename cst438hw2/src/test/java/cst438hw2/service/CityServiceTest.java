package cst438hw2.service;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;

import cst438hw2.domain.*;
 
@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	
	@Test
	public void contextLoads() {
	}


	@Test
	public void testCityFound() throws Exception {

		Country country = new Country("TST", "Test Country");
		City city = new City(1, "TestCity", country.getCode(),"DistrictTest", 100000);
		List<City> cities = new ArrayList<City>();
		TimeAndTemp timeAndTemp = new TimeAndTemp(300, 1593900461, 0);
		
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		given(countryRepository.findByCode(city.getCountryCode())).willReturn(country);
		given(weatherService.getTempAndTime("TestCity")).willReturn(timeAndTemp);
		
		// converts the temperature returned by timeAndTemp to fahrenheit.
		double tempF = Math.round((timeAndTemp.temp - 273.15) * 9.0/5.0 + 32.0);
		
		// sets long epoch time as Date class. Multiplies 1000 to put in millisecond format.
		Date time = new Date(timeAndTemp.time * 1000);
					
		// sets format to be used by date.
		SimpleDateFormat formatDate = new SimpleDateFormat("h:mm a");
				
		// formats time to formatDate.
		String timeString = formatDate.format(time);
		
		CityInfo cityResult = new CityInfo(city, country.getName(), tempF, timeString);
		
		CityInfo expectedResult = new CityInfo(city, "Test Country", 80, "3:07 PM");
		
		assertThat(cityResult).isEqualTo(expectedResult);
	}
	
	@Test 
	public void  testCityNotFound() {
		// TODO 
	}
	
	@Test 
	public void  testCityMultiple() {
		// TODO 
		
	}

}
