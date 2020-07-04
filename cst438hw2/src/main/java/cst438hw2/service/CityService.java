package cst438hw2.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438hw2.domain.*;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {	
		
		// get city info from the database.
		List<City> cities = cityRepository.findByName(cityName);
		
		// check how many cities were returned
		if (cities.size() == 0) {
			
			// city name was not found
			return null;
		} else {
			
			// if multiple cities, take the first one.
			City city = cities.get(0);
			
			// get country info using city
			Country country = countryRepository.findByCode(city.getCountryCode());
			
			// PROBABLY NEED FORMATTING HERE
			TimeAndTemp timeAndTemp = weatherService.getTempAndTime(cityName);
			Long time = timeAndTemp.time;
			
			return new CityInfo(city, country.getName(), timeAndTemp.temp, time.toString());
		}
	}
}
