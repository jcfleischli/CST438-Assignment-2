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
		List<City> city = cityRepository.findByName(cityName);
		Country country = countryRepository.findByCode(city.get(0).getCountryCode());
		TimeAndTemp timeAndTemp = weatherService.getTempAndTime(cityName);
		Long time = timeAndTemp.time;
		
		return new CityInfo(city.get(0), country.getName(), timeAndTemp.temp, time.toString());
	}
	
}
