package com.bootcamp.santiagomed93.hotelApi.service;

import java.util.List;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Country;
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.model.request.HotelRequest;

public interface DataSource {

	List<Country> findAllCountry();
	Country findCountryById(Long id);
	Country findCountryByName(String name);
	void saveCountry(Country country);
	void deleteCountryById(Long id);
	
	List<City> findAllCity();
	City findCityById(Long id);
	City findCityByName(String name);
	List<City> findCityByNameLike(String name);
	List<City> findCityByPopulationFrom(Long populationFrom);
	List<City> findCityByPopulationTo(Long populationTo);
	List<City> findCityByPopulationBetween(Long populationFrom, Long populationTo);
	void saveCity(City city);
	
	List<Hotel> findAllHotel();
	void saveHotel(HotelRequest hotelrequest);
}
