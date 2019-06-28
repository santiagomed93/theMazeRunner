package com.bootcamp.santiagomed93.hotelApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Country;
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.model.Room;
import com.bootcamp.santiagomed93.hotelApi.repository.CityRepository;
import com.bootcamp.santiagomed93.hotelApi.repository.CountryRepository;
import com.bootcamp.santiagomed93.hotelApi.repository.HotelRepository;
import com.bootcamp.santiagomed93.hotelApi.repository.RoomRepository;

@Service
public class DataSourceImpl implements DataSource{
	
	@Autowired
	private CountryRepository repoCountry;
	
	@Autowired
	private CityRepository repoCity;
	
	@Autowired
	private HotelRepository repoHotel;
	
	@Autowired
	private RoomRepository repoRoom;
	
	
	// Methods Country
	@Override
	public List<Country> findAllCountry() {
		return repoCountry.findAll();
	}
	
	@Override
	public Country findCountryById(Long id) {
		if(repoCountry.existsById(id)) {
			Country country = repoCountry.findById(id).get();
			return country;
		}
		return null;
	}
	
	@Override
	public Country findCountryByName(String name) {
		return repoCountry.findByName(name);
	}
	
	@Override
	public void saveCountry(Country country) {
		repoCountry.saveAndFlush(country);
	}
	
	@Override
	public void deleteCountryById(Long id) {
		repoCountry.deleteById(id);
	}	
	
	// Methods Cities
	@Override
	public List<City> findAllCity() {
		return repoCity.findAll();
	}
	
	@Override
	public City findCityById(Long id) {
		if(repoCity.existsById(id)) {
			return repoCity.findById(id).get();
		}
		return null;
	}
	
	@Override
	public City findCityByName(String name) {
		return repoCity.findByName(name);
	}
	
	@Override
	public List<City> findCityByNameLike(String name){
		return repoCity.findByNameContaining(name);
	}
	
	@Override
	public List<City> findCityByPopulationFrom(Long populationFrom){
		return repoCity.findByPopulationGreaterThanEqual(populationFrom);
	}
	
	@Override
	public List<City> findCityByPopulationTo(Long populationTo){
		return repoCity.findByPopulationLessThanEqual(populationTo);
	}
	
	@Override
	public List<City> findCityByPopulationBetween(Long populationFrom, Long populationTo){
		return repoCity.findByPopulationBetween(populationFrom, populationTo);
	}

	@Override
	public void saveCity(City city) {
		repoCity.saveAndFlush(city);	
	}
	
	@Override
	public void deleteCityById(Long id) {
		if(repoCity.existsById(id)) {
			City city = repoCity.findById(id).get();
			city.getCountry().getCities().remove(city);
			repoCountry.saveAndFlush(city.getCountry());
			repoCity.delete(city);
		}
	}
		
	// Methods Hotels
	@Override
	public List<Hotel> findAllHotel() {
		return repoHotel.findAll();
	}

	@Override
	public Hotel findHotelById(Long id) {
		if(repoHotel.existsById(id)) {
			return repoHotel.findById(id).get();
		}
		return null;
	}

	@Override
	public Hotel findHotelByName(String name) {
		return repoHotel.findByName(name);
	}

	@Override
	public List<Hotel> findHotelByNameLike(String name) {
		return repoHotel.findByNameContaining(name);
	}

	@Override
	public void saveHotel(Hotel hotel) {
		repoHotel.saveAndFlush(hotel);
	}

	@Override
	public void deleteHotelById(Long id) {
		if(repoHotel.existsById(id)) {
			Hotel hotel = repoHotel.findById(id).get();
			hotel.getCity().getHotels().remove(hotel);
			repoCity.saveAndFlush(hotel.getCity());
			repoHotel.delete(hotel);
		}		
	}
	
	// Methods Rooms
	@Override
	public List<Room> findAllRoom(){
		return repoRoom.findAll();
	}
	
	@Override
	public void saveRoom(Room room) {
		repoRoom.saveAndFlush(room);
	}
	
	@Override
	public Room findRoomById(Long id) {
		if(repoRoom.existsById(id)) {
			return repoRoom.findById(id).get();
		}
		return null;
	}
	
	@Override
	public void deleteRoomById(Long id) {
		if(repoRoom.existsById(id)) {
			Room room = repoRoom.findById(id).get();
			room.getHotel().getRooms().remove(room);
			repoHotel.saveAndFlush(room.getHotel());
			repoRoom.delete(room);
		}	
	}
	
	@Override
	public List<Room> findRoomByFilter(Integer costFrom, Integer costTo, Integer capacity){
		if(costFrom!= null && costTo == null && capacity == null) {
			return repoRoom.findByCostGreaterThanEqual(costFrom);
		}
		
		if(costFrom!= null && costTo == null && capacity != null) {
			return repoRoom.findByCostGreaterThanEqualAndCapacity(costFrom, capacity);
		}
		
		if(costFrom== null && costTo != null && capacity == null) {
			return repoRoom.findByCostLessThanEqual(costTo);
		}
		
		if(costFrom== null && costTo != null && capacity != null) {
			return repoRoom.findByCostLessThanEqualAndCapacity(costTo, capacity);
		}
		
		if(costFrom!= null && costTo != null && capacity == null) {
			return repoRoom.findByCostBetween(costFrom, costTo);
		}
		
		if(costFrom!= null && costTo != null && capacity != null) {
			return repoRoom.findByCostBetweenAndCapacity(costFrom, costTo, capacity);
		}
		return null;
	}

	
}
