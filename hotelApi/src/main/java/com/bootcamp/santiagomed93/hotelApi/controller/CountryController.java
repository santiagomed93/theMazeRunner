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
import com.bootcamp.santiagomed93.hotelApi.model.Country;
import com.bootcamp.santiagomed93.hotelApi.service.DataSource;

@RestController
@RequestMapping("/countries")
public class CountryController {
	
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
	public ResponseEntity<List<Country>> listCountries(){
		return  new ResponseEntity<>(datasource.findAllCountry(), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Country> createCountry(@RequestBody @Valid Country country){
		datasource.saveCountry(country);
		return new ResponseEntity<>(country, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> collectionOptions2(){
		return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable("id") Long id){
		Country country = datasource.findCountryById(id);
		if(country != null) {
			return new ResponseEntity<>(country, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCountry(@PathVariable("id") Long id, @RequestBody @Valid Country country){
		Country countryData = datasource.findCountryById(id);
		if(countryData != null) {
			country.setId(countryData.getId());
			datasource.saveCountry(country);
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCountry(@PathVariable("id") Long id) {
		datasource.deleteCountryById(id);
	}
	
	@RequestMapping(value="/filter", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> collectionOptions3(){
		return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.OPTIONS)
                .build();
	}
	
	@GetMapping("/filter")
	public ResponseEntity<Country> getCountryByName(@RequestParam("name") String name){
		Country country = datasource.findCountryByName(name);
		if(country != null) {
			return new ResponseEntity<>(country, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
		
}
