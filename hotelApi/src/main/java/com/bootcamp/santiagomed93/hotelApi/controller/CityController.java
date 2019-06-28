package com.bootcamp.santiagomed93.hotelApi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Country;
import com.bootcamp.santiagomed93.hotelApi.service.DataSource;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private DataSource datasource;
	
	
	@RequestMapping(method = RequestMethod.OPTIONS)
	public ResponseEntity<?> collectionOptions1(){
		return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                .build();
	}
	
	@GetMapping()
	public ResponseEntity<List<City>> listCountries(){
		return  new ResponseEntity<>(datasource.findAllCity(), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<City> createCountry(@RequestBody @Valid City city){
		if(city.getCountry() != null) {
			Country country = datasource.findCountryById(city.getCountry().getId());
			if(country != null) {
				city.setCountry(country);
				datasource.saveCity(city);
				return new ResponseEntity<>(city, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> collectionOptions2(){
		return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<City> getCityById(@PathVariable("id") Long id){
		City city = datasource.findCityById(id);
		if(city != null) {
			return new ResponseEntity<>(city, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCity (@PathVariable("id") Long id, @RequestBody @Valid City city){
		City cityData = datasource.findCityById(id);
		if(cityData != null) {
			city.setId(cityData.getId());
			if(city.getCountry().getId()!= null) {
				Country countryData = datasource.findCountryById(city.getCountry().getId());
				if(countryData == null) {
					return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
				}
				city.setCountry(countryData);
			}
			datasource.saveCity(city);
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCity(@PathVariable("id") Long id) {
		datasource.deleteCityById(id);
	}
	
	@RequestMapping(value="/filter", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> collectionOptions3(){
		return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
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
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	
}
