package com.bootcamp.santiagomed93.hotelApi.service;

import java.util.List;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Country;
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.model.Room;

public interface DataSourcePublic {

	List<Country> publicAllCountry();
	List<City> publicAllCity(Long idCountry);
	List<Hotel> publicAllHotel(Long idCountry, Long idCity);
	List<Room> publicAllRoom(Long idCountry, Long idCity, Long idHotel);
	Room publicRoomByHotel(Long idCountry, Long idCity, Long idHotel, Long idRoom);
}
