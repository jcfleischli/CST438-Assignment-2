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
		
		// check how many cities were returned.
		if (cities.size() == 0) {
			
			// city name was not found.
			return null;
		} else {
			
			// if multiple cities, take the first one.
			City city = cities.get(0);
			
			// get country info using city.
			Country country = countryRepository.findByCode(city.getCountryCode());
			
			// gets time and temperature from weather service using cityName.
			TimeAndTemp timeAndTemp = weatherService.getTimeAndTemp(cityName);
			
			// converts the temperature returned by timeAndTemp to fahrenheit.
			double tempF = Math.round((timeAndTemp.temp - 273.15) * 9.0/5.0 + 32.0);
			
			// sets long epoch time as Date class. Multiplies 1000 to put in millisecond format.
			Date time = new Date(timeAndTemp.time * 1000);
			
			// sets format to be used by date.
			SimpleDateFormat formatDate = new SimpleDateFormat("h:mm a");
			
			// formats time to formatDate.
			String timeString = formatDate.format(time);

			return new CityInfo(city, country.getName(), tempF, timeString);
		}
	}
}
