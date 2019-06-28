package com.bootcamp.santiagomed93.hotelApi.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Country;
import com.bootcamp.santiagomed93.hotelApi.service.DataSource;

@RestController
public class MixController {
	
	@Autowired
	private DataSource datasource;

	@GetMapping("/countries/{id}/cities")
	public ResponseEntity<Set<City>> getCitiesByCountryId(@PathVariable("id") Long id){
		Country country = datasource.findCountryById(id);
		if(country != null) {
			Set<City> cities =country.getCities();
			if(!cities.isEmpty()) {
				return new ResponseEntity<>(cities,HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/countries/{id}/cities")
	public ResponseEntity<List<City>> updateCitiesInACountry(@PathVariable("id") Long id, @RequestBody List<City> cities) {
		Country country = datasource.findCountryById(id);
		if(country != null) {
			for(City city : cities) {
				city.setCountry(country);
				datasource.saveCity(city);
			}
			return new ResponseEntity<>(cities,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/countries/{idCountry}/cities/{idCity}")
	public ResponseEntity<City> getCityByCityIdAndByCountryId(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity){
		Country country = datasource.findCountryById(idCountry);
		Set<City> cities =country.getCities();
		if(!cities.isEmpty()) {
			for(City city : cities) {
				if(city.getId() == idCity) {
					return new ResponseEntity<>(city,HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
	}
	
}
