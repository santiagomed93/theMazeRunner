package com.bootcamp.santiagomed93.hotelApi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.service.DataSource;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private DataSource datasource;
	
	@GetMapping()
	public ResponseEntity<List<City>> listCountries(){
		return  new ResponseEntity<>(datasource.findAllCity(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<City> getCityById(@PathVariable("id") Long id){
		City city = datasource.findCityById(id);
		if(city != null) {
			return new ResponseEntity<>(city, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<City>> getCitiesByFilter(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "populationFrom", required = false) Long populationFrom, @RequestParam(value = "populationTo", required = false) Long populationTo){
		if(
				(name==null && populationFrom == null && populationTo == null) ||
				(name!=null && populationFrom != null && populationTo != null) ||
				(name!=null && (populationFrom != null || populationTo != null))
				
			) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		if(name != null && populationFrom == null && populationTo == null) {
			List<City> cities = datasource.findCityByNameLike(name);
			if(!cities.isEmpty()) {
				return new ResponseEntity<>(cities, HttpStatus.OK);
			}
		}
		
		if(name == null && populationFrom != null && populationTo == null) {
			List<City> cities = datasource.findCityByPopulationFrom(populationFrom);
			if(!cities.isEmpty()) {
				return new ResponseEntity<>(cities, HttpStatus.OK);
			}	
		}
		
		if(name == null && populationFrom == null && populationTo != null) {
			List<City> cities = datasource.findCityByPopulationFrom(populationFrom);
			if(!cities.isEmpty()) {
				return new ResponseEntity<>(cities, HttpStatus.OK);
			}	
		}
		
		if(name == null && populationFrom != null && populationTo != null) {
			List<City> cities = datasource.findCityByPopulationBetween(populationFrom, populationTo);
			if(!cities.isEmpty()) {
				return new ResponseEntity<>(cities, HttpStatus.OK);
			}	
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping()
	public ResponseEntity<City> createCountry(@RequestBody @Valid City city){
		System.out.println(city);
		datasource.saveCity(city);
		System.out.println(datasource.findAllCity());
		return new ResponseEntity<>(city, HttpStatus.CREATED);
	}
	
}
