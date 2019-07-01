package com.bootcamp.santiagomed93.hotelApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
@Profile("dev")
public class HotelApiInitializingBeanDev implements InitializingBean{

	@Autowired
	CityRepository cityRepo;
	
	@Autowired
	CountryRepository countryRepo;
	
	@Autowired
	HotelRepository hotelRepo;
	
	@Autowired
	RoomRepository roomRepo;
	
	
	
	@Override
	@Transactional()
	public void afterPropertiesSet() throws Exception {
		System.out.println("Bootstraping data");
		createDataBase();
	}
	
	private void createDataBase() {
		LocalDate localDate = LocalDate.now();
		System.out.println("localDate: " + localDate);
		////////////////
		//Set Countries
		////////////////
		Country country1 = new Country();
		country1.setName("Colombia");
		countryRepo.saveAndFlush(country1);
		
		////////////////
		//Set Cities
		////////////////
		//----------------- Country 1 --------------------------------//
		City city1 = new City();
		city1.setName("Bogota");
		city1.setPopulation(8081000L);
		city1.setCountry(countryRepo.findById(country1.getId()).get());
		cityRepo.saveAndFlush(city1);
		
		City city3 = new City();
		city3.setName("Popayan");
		city3.setPopulation(280107L);
		city3.setCountry(countryRepo.findById(country1.getId()).get());
		cityRepo.saveAndFlush(city3);
		
		
		
		////////////////
		//Set Hotels
		////////////////
		
		//----------------- Hotels Bogota --------------------------------//
		Hotel hotel1 = new Hotel();
		hotel1.setName("Selina");
		hotel1.setAddress("Cl. 74 ## 15-60, Bogotá, Cundinamarca");
		hotel1.setDescription("Ubicación Excelente en general para turismo, diversión y medios de transporte");
		hotel1.setCity(cityRepo.findById(city1.getId()).get());
		hotelRepo.saveAndFlush(hotel1);
		
		Hotel hotel2 = new Hotel();
		hotel2.setName("Hilton Garden");
		hotel2.setAddress("Carrera 82 No. 25G 84, Bogotá, Cundinamarca");
		hotel2.setDescription("Ubicación Excelente en general para turismo, diversión y medios de transporte");
		hotel2.setCity(cityRepo.findById(city1.getId()).get());
		hotelRepo.saveAndFlush(hotel2);
		
		//----------------- Hotels Popayan --------------------------------//
		Hotel hotel7 = new Hotel();
		hotel7.setName("Krone");
		hotel7.setAddress("calle 7 # 7-78, 190001 Popayán, Cauca");
		hotel7.setDescription("Ubicación Excelente en general para turismo, diversión y medios de transporte");
		hotel7.setCity(cityRepo.findById(city3.getId()).get());
		hotelRepo.saveAndFlush(hotel7);
		
		Hotel hotel8 = new Hotel();
		hotel8.setName("La Plazuela");
		hotel8.setAddress("Calle 5 No. 8-13, 190003 Popayán, Cauca");
		hotel8.setDescription("Ubicación Excelente en general para turismo, diversión y medios de transporte");
		hotel8.setCity(cityRepo.findById(city3.getId()).get());
		hotelRepo.saveAndFlush(hotel8);
		
		
		
		List<Hotel> hotels = hotelRepo.findAll();
		
		
		
		////////////////
		//Set Rooms
		////////////////
		
		Room room101 = new Room();
		room101.setNumberRoom("101");
		room101.setSingleBed(2);
		room101.setDoubleBed(0);
		room101.setCost(100);
		room101.setCapacity(2);
		
		Room room102 = new Room();
		room102.setNumberRoom("102");
		room102.setSingleBed(1);
		room102.setDoubleBed(1);
		room102.setCost(150);
		room102.setCapacity(3);		
		
		
		List<Room> rooms = new ArrayList<>();
		rooms.add(room101);
		rooms.add(room102);
		
		System.out.println(hotels.size());
		for(Hotel hotel : hotels) {
			for (Room room : rooms) {
				room.setHotel(hotel);
				roomRepo.saveAndFlush(room);
				room.setId(null);
			}
		}
		
		
	}

}
