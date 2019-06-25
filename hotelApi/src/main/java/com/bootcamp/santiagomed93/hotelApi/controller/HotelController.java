package com.bootcamp.santiagomed93.hotelApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.santiagomed93.hotelApi.service.DataSource;
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.model.request.HotelRequest;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private DataSource datasource;
	
	@GetMapping()
	public ResponseEntity<List<Hotel>> listHotel(){
		return  new ResponseEntity<>(datasource.findAllHotel(), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Hotel> createHotel(@RequestBody HotelRequest hotel){
		
		return new ResponseEntity<>(null,HttpStatus.CREATED);
	}
	
	

}
