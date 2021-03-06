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

import com.bootcamp.santiagomed93.hotelApi.service.DataSource;
import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
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
	public ResponseEntity<List<Hotel>> listHotel(){
		return  new ResponseEntity<>(datasource.findAllHotel(), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Hotel> createHotel(@RequestBody @Valid Hotel hotel){
		if(hotel.getCity() != null) {
			City city = datasource.findCityById(hotel.getCity().getId());
			if(city!= null) {
				datasource.saveHotel(hotel);
				return new ResponseEntity<>(hotel, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> collectionOptions2(){
		return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Long id){
		Hotel hotel = datasource.findHotelById(id);
		if(hotel != null) {
			return new ResponseEntity<>(hotel, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateHotel(@PathVariable("id") Long id, @RequestBody @Valid Hotel hotel){
		Hotel hotelData = datasource.findHotelById(id);
		if(hotelData != null) {
			hotel.setId(hotelData.getId());	
			if(hotel.getCity().getId() != null) {
				City cityData = datasource.findCityById(hotel.getCity().getId());
				if(cityData == null) {
					return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
				}
				hotel.setCity(cityData);
			}
			
			datasource.saveHotel(hotel);
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);		
	}
	
	@DeleteMapping("/{id}")
	public void deleteHotel(@PathVariable("id") Long id) {
		datasource.deleteHotelById(id);
	}
	
	@RequestMapping(value="/filter", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> collectionOptions3(){
		return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<Hotel>> getHotelByName(@RequestParam("name") String name){
		List<Hotel> hotels = datasource.findHotelByNameLike(name);
		if(!hotels.isEmpty()) {
			return new ResponseEntity<>(hotels, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
}
