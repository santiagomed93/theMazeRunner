package com.bootcamp.santiagomed93.hotelApi.service;

import java.time.LocalDate;
import java.util.List;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Country;
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.model.Reservation;
import com.bootcamp.santiagomed93.hotelApi.model.Room;

public interface DataSource {

	List<Country> findAllCountry();
	Country findCountryById(Long id);
	Country findCountryByName(String name);
	void saveCountry(Country country);
	void deleteCountryById(Long id);
	
	List<City> findAllCity();
	List<City> findCityByCountry(Country country);
	City findCityById(Long id);
	City findCityByName(String name);
	List<City> findCityByNameLike(String name);
	List<City> findCityByPopulationFrom(Long populationFrom);
	List<City> findCityByPopulationTo(Long populationTo);
	List<City> findCityByPopulationBetween(Long populationFrom, Long populationTo);
	void saveCity(City city);
	void deleteCityById(Long id);
	
	List<Hotel> findAllHotel();
	Hotel findHotelById(Long id);
	Hotel findHotelByName(String name);
	List<Hotel> findHotelByNameLike(String name);
	List<Hotel> findHotelByCity(City city);
	void saveHotel(Hotel hotel);
	void deleteHotelById(Long id);
	
	List<Room> findAllRoom();
	List<Room> findRoomByHotel(Hotel hotel);
	Room findRoomById(Long id);
	List<Room> findRoomByFilter(Integer costFrom, Integer costTo, Integer capacity);
	void saveRoom(Room room);
	void deleteRoomById(Long id);
	
	List<Reservation> findReservationByRoom(Room room);
	boolean checkDatesReservation(LocalDate startDate, LocalDate endDate);
	void saveReservation(Reservation reservation, Room room);
	
}
