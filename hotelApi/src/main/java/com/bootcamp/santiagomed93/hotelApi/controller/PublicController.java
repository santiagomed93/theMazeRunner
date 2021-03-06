package com.bootcamp.santiagomed93.hotelApi.controller;

import java.util.List;

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
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.model.Reservation;
import com.bootcamp.santiagomed93.hotelApi.model.Room;
import com.bootcamp.santiagomed93.hotelApi.service.DataSourcePublic;

@RestController
public class PublicController {

	@Autowired
	private DataSourcePublic datasource;
	
	@GetMapping("/public/countries")
	public List<Country> getCountriesWithHotels(){
		return datasource.publicAllCountry();
	}
	
	@GetMapping("/public/countries/{idCountry}/cities")
	public ResponseEntity<List<City>> getCitiesByCountry(@PathVariable("idCountry") Long idCountry){
		List<City> cities = datasource.publicAllCity(idCountry);
		if(cities != null) {
			return new ResponseEntity<>(cities,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/public/countries/{idCountry}/cities/{idCity}/hotels")
	public ResponseEntity<List<Hotel>> getHotelsByCity(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity){
		List<Hotel> hotels = datasource.publicAllHotel(idCountry, idCity);
		if(hotels != null) {
			return new ResponseEntity<>(hotels, HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/public/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms")
	public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel){
		List<Room> rooms = datasource.publicAllRoom(idCountry, idCity, idHotel);
		if(rooms != null) {
			return new ResponseEntity<>(rooms, HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/public/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms/{idRoom}")
	public ResponseEntity<Room> getRoomByHotelRoom(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel, @PathVariable("idRoom") Long idRoom){
		Room room = datasource.publicRoomByHotel(idCountry, idCity, idHotel, idRoom);
		if(room != null) {
			return new ResponseEntity<>(room, HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/public/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms/{idRoom}/reservations")
	public ResponseEntity<List<Reservation>> getReservationByRoomHotel(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel, @PathVariable("idRoom") Long idRoom){
		List<Reservation> reservations = datasource.publicReservationByRoom(idCountry, idCity, idHotel, idRoom);
		if(reservations != null) {
			return new ResponseEntity<>(reservations, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/public/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms/{idRoom}/reservations")
	public ResponseEntity<Reservation> createReservationByRoom(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel, @PathVariable("idRoom") Long idRoom, @RequestBody Reservation reservation){
		Room room = datasource.publicRoomByHotel(idCountry, idCity, idHotel, idRoom);
		if(room != null) {
			if(reservation.getStartDate().isEqual(reservation.getEndDate()) || reservation.getStartDate().isBefore(reservation.getEndDate())) {
				if(datasource.checkDatesReservation(reservation.getStartDate(), reservation.getEndDate())) {
					datasource.saveReservation(reservation, room);
					return new ResponseEntity<>(reservation, HttpStatus.OK);
				}
				return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
}
