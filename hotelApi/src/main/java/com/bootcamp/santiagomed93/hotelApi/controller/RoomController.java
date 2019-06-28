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

import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.model.Room;
import com.bootcamp.santiagomed93.hotelApi.service.DataSource;

@RestController
@RequestMapping("/rooms")
public class RoomController {

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
	public ResponseEntity<List<Room>> listRoom(){
		return  new ResponseEntity<>(datasource.findAllRoom(), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Room> createRoom(@RequestBody @Valid Room room){
		if(room.getHotel() != null) {
			Hotel hotel = datasource.findHotelById(room.getHotel().getId());
			if(hotel != null) {
				room.setCapacity(room.getSingleBed() + room.getDoubleBed());
				datasource.saveRoom(room);
				return new ResponseEntity<>(room, HttpStatus.CREATED);
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
	public ResponseEntity<Room> getRoomById(@PathVariable("id") Long id){
		Room room = datasource.findRoomById(id);
		if(room != null) {
			return new ResponseEntity<>(room, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRoom(@PathVariable("id") Long id, @RequestBody @Valid Room room){
		Room roomData = datasource.findRoomById(id);
		if(roomData != null) {
			room.setId(roomData.getId());
			if(room.getHotel().getId()!= null) {
				Hotel hotel = datasource.findHotelById(room.getHotel().getId());
				if(hotel == null) {
					return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
				}
				room.setHotel(hotel);
			}
			datasource.saveRoom(room);
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);		
	}
	
	@DeleteMapping("/{id}")
	public void deleteRoom(@PathVariable("id") Long id) {
		datasource.deleteRoomById(id);
	}
	
	@RequestMapping(value="/filter", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> collectionOptions3(){
		return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<Room>> getRoomByFilter(@RequestParam(value = "costFrom", required = false) Integer costFrom, @RequestParam(value = "costTo", required = false) Integer costTo, @RequestParam(value = "capacity", required = false) Integer capacity){
		if(costFrom == null && costTo == null && capacity == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		List<Room> list = datasource.findRoomByFilter(costFrom, costTo, capacity);
		if(!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
}
