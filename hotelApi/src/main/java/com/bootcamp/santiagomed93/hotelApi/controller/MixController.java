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
import com.bootcamp.santiagomed93.hotelApi.service.DataSource;

@RestController
public class MixController {
	
	@Autowired
	private DataSource datasource;

	@GetMapping("/countries/{id}/cities")
	public ResponseEntity<List<City>> getCitiesByCountryId(@PathVariable("id") Long idCountry){
		Country country = datasource.findCountryById(idCountry);
		if(country != null) {
			List<City> cities =datasource.findCityByCountry(country);
			if(!cities.isEmpty()) {
				return new ResponseEntity<>(cities,HttpStatus.OK);
			}
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/countries/{id}/cities")
	public ResponseEntity<List<City>> updateCitiesInACountry(@PathVariable("id") Long idCountry, @RequestBody List<City> cities) {
		Country country = datasource.findCountryById(idCountry);
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
		ResponseEntity<List<City>> responseCities = getCitiesByCountryId(idCountry);
		List<City> cities = responseCities.getBody();
		if(cities != null) {
			for (City city : cities) {
				if(city.getId() == idCity) {
					return new ResponseEntity<>(city,HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null,responseCities.getStatusCode());
	}
	
	@GetMapping("/countries/{idCountry}/cities/{idCity}/hotels")
	public ResponseEntity<List<Hotel>> getHotelsByCity(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity){
		ResponseEntity<City> responseCity = getCityByCityIdAndByCountryId(idCountry,idCity);
		City city = responseCity.getBody();
		if(city != null) {
			if(!city.getHotels().isEmpty()) {
				List<Hotel> hotels = datasource.findHotelByCity(city);
				return new ResponseEntity<>(hotels,HttpStatus.OK);
			}
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(null,responseCity.getStatusCode());	
	}
	
	@GetMapping("/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}")
	public ResponseEntity<Hotel> getHotelByHotelIdAndCityId(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel){
		ResponseEntity<List<Hotel>> responseHotels = getHotelsByCity(idCountry,idCity);
		List<Hotel> hotels = responseHotels.getBody();
		if(hotels != null) {
			for(Hotel hotel : hotels) {
				if(hotel.getId() == idHotel) {
					return new ResponseEntity<>(hotel, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, responseHotels.getStatusCode());
	}
	
	@GetMapping("/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms")
	public ResponseEntity<List<Room>> getRoomsByHotelIdAndCityId(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel){
		ResponseEntity<Hotel> responseHotel = getHotelByHotelIdAndCityId(idCountry,idCity,idHotel);
		Hotel hotel = responseHotel.getBody();
		if(hotel != null) {
			if(!hotel.getRooms().isEmpty()) {
				List<Room> rooms = datasource.findRoomByHotel(hotel);
				return new ResponseEntity<>(rooms,HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(null, responseHotel.getStatusCode());
	}
	
	@GetMapping("/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms/{idRoom}")
	public ResponseEntity<Room> getRoomByRoomIdAndHotelId(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel, @PathVariable("idRoom") Long idRoom){
		ResponseEntity<List<Room>> responseRooms = getRoomsByHotelIdAndCityId(idCountry,idCity,idHotel);
		List<Room> rooms = responseRooms.getBody();
		if(rooms != null) {
			for(Room room : rooms) {
				if(room.getId() == idRoom) {
					return new ResponseEntity<>(room, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, responseRooms.getStatusCode());
	}
	
	@GetMapping("/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms/{idRoom}/reservations")
	public ResponseEntity<List<Reservation>> getReservationsByRoomIdAndHotelId(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel, @PathVariable("idRoom") Long idRoom){
		ResponseEntity<Room> responseRoom = getRoomByRoomIdAndHotelId(idCountry,idCity,idHotel, idRoom);
		Room room = responseRoom.getBody();
		if(room != null) {
			if(!room.getReservations().isEmpty()) {
				List<Reservation> reservations = datasource.findReservationByRoom(room);
				return new ResponseEntity<>(reservations, HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(null, responseRoom.getStatusCode());
	}
	
	@PostMapping("/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms/{idRoom}/reservations")
	public ResponseEntity<Object> createReservationByRoomIdAndHotelId(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel, @PathVariable("idRoom") Long idRoom, @RequestBody Reservation reservation){
		ResponseEntity<Room> responseRoom = getRoomByRoomIdAndHotelId(idCountry,idCity,idHotel, idRoom);
		Room room = responseRoom.getBody();
		if(room != null) {
			if(reservation.getStartDate().isEqual(reservation.getEndDate()) || reservation.getStartDate().isBefore(reservation.getEndDate())) {
				if(datasource.checkDatesReservation(reservation.getStartDate(), reservation.getEndDate())) {
					datasource.saveReservation(reservation, room);
					return new ResponseEntity<>(reservation, HttpStatus.OK);
				}
				return new ResponseEntity<>("Habitacion Reservada en esa fecha",HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, responseRoom.getStatusCode());
	}
	
}
