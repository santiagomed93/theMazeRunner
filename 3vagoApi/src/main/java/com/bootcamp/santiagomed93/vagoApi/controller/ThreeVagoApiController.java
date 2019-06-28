package com.bootcamp.santiagomed93.vagoApi.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.santiagomed93.vagoApi.model.City;
import com.bootcamp.santiagomed93.vagoApi.model.Country;
import com.bootcamp.santiagomed93.vagoApi.model.Hotel;
import com.bootcamp.santiagomed93.vagoApi.model.Room;

@RestController
public class ThreeVagoApiController {
	
	private final String urlBase = "http://localhost:8101/public";
	
	@GetMapping("/countries")
	public ResponseEntity<List<Country>> getListCountry(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Country>> response = restTemplate.exchange(
		  urlBase+"/countries/",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<Country>>(){});
		System.out.println(response.getBody());
		List<Country> countries = response.getBody();
		return new ResponseEntity<>(countries, response.getStatusCode());
	}
	
	@GetMapping("/countries/{id}/cities")
	public ResponseEntity<List<City>> getListCitiesByCountry(@PathVariable("id") Long id){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<City>> response = restTemplate.exchange(
		  urlBase+"/countries/"+id+"/cities",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<City>>(){});
		List<City> cities = response.getBody();
		return new ResponseEntity<>(cities, response.getStatusCode());
	}
	
	@GetMapping("/countries/{idCountry}/cities/{idCity}/hotels")
	public ResponseEntity<List<Hotel>> getListHotelsByCountry(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Hotel>> response = restTemplate.exchange(
		  urlBase+"/countries/"+idCountry+"/cities/"+idCity+"/hotels",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<Hotel>>(){});
		List<Hotel> hotels = response.getBody();
		return new ResponseEntity<>(hotels, response.getStatusCode());
	}
	
	@GetMapping("/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms")
	public ResponseEntity<List<Room>> getListRoomsByHotels(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Room>> response = restTemplate.exchange(
		  urlBase+"/countries/"+idCountry+"/cities/"+idCity+"/hotels/"+idHotel+"/rooms",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<Room>>(){});
		List<Room> rooms = response.getBody();
		return new ResponseEntity<>(rooms, response.getStatusCode());
	}
	
	@GetMapping("/countries/{idCountry}/cities/{idCity}/hotels/{idHotel}/rooms/{idRoom}")
	public ResponseEntity<Room> getListRoomByHotelRooms(@PathVariable("idCountry") Long idCountry, @PathVariable("idCity") Long idCity, @PathVariable("idHotel") Long idHotel, @PathVariable("idRoom") Long idRoom){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Room> response = restTemplate.exchange(
		  urlBase+"/countries/"+idCountry+"/cities/"+idCity+"/hotels/"+idHotel+"/rooms/"+idRoom,
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<Room>(){});
		Room room = response.getBody();
		return new ResponseEntity<>(room, response.getStatusCode());
	}

}
