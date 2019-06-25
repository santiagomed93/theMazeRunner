package com.bootcamp.santiagomed93.hotelApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Country;
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.model.request.HotelRequest;
import com.bootcamp.santiagomed93.hotelApi.repository.CityRepository;
import com.bootcamp.santiagomed93.hotelApi.repository.CountryRepository;
import com.bootcamp.santiagomed93.hotelApi.repository.HotelRepository;

@Service
public class DataSourceImpl implements DataSource{
	
	@Autowired
	private CountryRepository repoCountry;
	
	@Autowired
	private CityRepository repoCity;
	
	@Autowired
	private HotelRepository repoHotel;
	
	
	
	
	// Methods Country
	@Override
	public List<Country> findAllCountry() {
		return repoCountry.findAll();
	}
	
	@Override
	public Country findCountryById(Long id) {
		if(repoCountry.existsById(id)) {
			return repoCountry.findById(id).get();
		}
		return null;
	}
	
	@Override
	public Country findCountryByName(String name) {
		return repoCountry.findByName(name);
	}
	
	@Override
	public void saveCountry(Country country) {
		repoCountry.saveAndFlush(country);
	}
	
	@Override
	public void deleteCountryById(Long id) {
		if(repoCountry.existsById(id)) {
			List<City> cities = repoCountry.findById(id).get().getCities();
			for(City city : cities) {
				city.setCountry(null);
				repoCity.save(city);
			}
		}
		
		repoCountry.deleteById(id);
	}
	
	
	// Methods Cities
	@Override
	public List<City> findAllCity() {
		return repoCity.findAll();
	}
	
	@Override
	public City findCityById(Long id) {
		if(repoCity.existsById(id)) {
			return repoCity.findById(id).get();
		}
		return null;
	}
	
	@Override
	public City findCityByName(String name) {
		return repoCity.findByName(name);
	}
	
	@Override
	public List<City> findCityByNameLike(String name){
		return repoCity.findByNameContaining(name);
	}
	
	@Override
	public List<City> findCityByPopulationFrom(Long populationFrom){
		return repoCity.findByPopulationGreaterThanEqual(populationFrom);
	}
	
	@Override
	public List<City> findCityByPopulationTo(Long populationTo){
		return repoCity.findByPopulationLessThanEqual(populationTo);
	}
	
	@Override
	public List<City> findCityByPopulationBetween(Long populationFrom, Long populationTo){
		return repoCity.findByPopulationBetween(populationFrom, populationTo);
	}

	@Override
	public void saveCity(City city) {
		repoCity.saveAndFlush(city);	
	}

	
	// Methods Hotels
	@Override
	public List<Hotel> findAllHotel() {
		return repoHotel.findAll();
	}

	@Override
	public void saveHotel(HotelRequest hotelrequest) {
		Hotel hotel = new Hotel();
		repoHotel.save(hotel);		
	}

	
}
