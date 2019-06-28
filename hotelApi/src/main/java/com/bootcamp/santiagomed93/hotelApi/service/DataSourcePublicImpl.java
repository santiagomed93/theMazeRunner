package com.bootcamp.santiagomed93.hotelApi.service;

import java.util.ArrayList;
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
public class DataSourcePublicImpl implements DataSourcePublic{

	@Autowired
	private CountryRepository repoCountry;
	
	@Autowired
	private CityRepository repoCity;
	
	@Autowired
	private HotelRepository repoHotel;
	
	@Autowired
	private RoomRepository repoRoom;
	
	@Override
	public List<Country> publicAllCountry(){
		List<Country> countries = repoCountry.findByCitiesIsNotEmpty();
		for(Country country : countries) {
			List<City> list = new ArrayList<>();
			for(City city : country.getCities()) {
				if (city.getHotels().isEmpty()) {
					list.add(city);
				}
			}
			country.getCities().removeAll(list);
		}
		
		List<Country> list = new ArrayList<>();
		for(Country country : countries) {
			
			if(country.getCities().isEmpty()) {
				list.add(country);
			}
		}
		countries.removeAll(list);
		return countries;
	}
	
	@Override
	public List<City> publicAllCity(Long idCountry){
		if(repoCountry.existsById(idCountry)) {
			Country country = repoCountry.findById(idCountry).get();
			List<Country> countries = publicAllCountry();
			if(countries.indexOf(country) != -1) {
				List<City> cities = repoCity.findByCountry(country);
				return cities;
			}	
		}
		return null;
	}
	
	@Override
	public List<Hotel> publicAllHotel(Long idCountry, Long idCity){
		List<City> cities = publicAllCity(idCountry);
		if(cities != null) {
			boolean contains = false;
			int index = 0;
			for(City city : cities) {
				if(city.getId() == idCity) {
					contains = true;
					index = cities.indexOf(city);
				}
			}
			if(contains) {
				City city = cities.get(index);
				List<Hotel> hotels = repoHotel.findByCity(city);
				return hotels;
			}
			
		}
		
		return null;
	}

	@Override
	public List<Room> publicAllRoom(Long idCountry, Long idCity, Long idHotel) {
		List<Hotel> hotels = publicAllHotel(idCountry, idCity);
		if(hotels != null) {
			boolean contains = false;
			int index = 0;
			for(Hotel hotel : hotels) {
				if(hotel.getId() == idHotel) {
					contains = true;
					index = hotels.indexOf(hotel);
				}
			}
			if(contains) {
				Hotel hotel = hotels.get(index);
				List<Room> rooms = repoRoom.findByHotel(hotel);
				return rooms;
			}
		}
		return null;
	}

	@Override
	public Room publicRoomByHotel(Long idCountry, Long idCity, Long idHotel, Long idRoom) {
		List<Room> rooms = publicAllRoom(idCountry, idCity, idHotel);
		if(rooms != null) {
			boolean contains = false;
			int index = 0;
			for(Room room : rooms) {
				if(room.getId() == idRoom) {
					contains = true;
					index = rooms.indexOf(room);
				}
			}
			if(contains) {
				Room room = rooms.get(index);
				return room;
			}
		}
		return null;
	}
	
}
