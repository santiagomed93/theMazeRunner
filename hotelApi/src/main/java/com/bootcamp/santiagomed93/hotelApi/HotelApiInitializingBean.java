package com.bootcamp.santiagomed93.hotelApi;

import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Country;
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.repository.CityRepository;
import com.bootcamp.santiagomed93.hotelApi.repository.CountryRepository;
import com.bootcamp.santiagomed93.hotelApi.repository.HotelRepository;

@Service
public class HotelApiInitializingBean implements InitializingBean{

	@Autowired
	CityRepository cityRepo;
	
	@Autowired
	CountryRepository countryRepo;
	
	@Autowired
	HotelRepository hotelRepo;
	
	
	
	@Override
	@Transactional()
	public void afterPropertiesSet() throws Exception {
		System.out.println("Bootstraping data");
		createDataBase();
	}
	
	private void createDataBase() {
		
		////////////////
		//Set Countries
		////////////////
		Country country1 = new Country();
		country1.setName("Colombia");
		countryRepo.saveAndFlush(country1);
		
		Country country2 = new Country();
		country2.setName("Holanda");
		countryRepo.saveAndFlush(country2);
		
		Country country3 = new Country();
		country3.setName("Australia");
		countryRepo.saveAndFlush(country3);
		
		////////////////
		//Set Cities
		////////////////
		//----------------- Country 1 --------------------------------//
		City city1 = new City();
		city1.setName("Bogota");
		city1.setPopulation(8081000L);
		city1.setCountry(countryRepo.findById(country1.getId()).get());
		cityRepo.saveAndFlush(city1);
		
		City city2 = new City();
		city2.setName("Medellin");
		city2.setPopulation(2508000L);
		city2.setCountry(countryRepo.findById(country1.getId()).get());
		cityRepo.saveAndFlush(city2);
		
		City city3 = new City();
		city3.setName("Popayan");
		city3.setPopulation(280107L);
		city3.setCountry(countryRepo.findById(country1.getId()).get());
		cityRepo.saveAndFlush(city3);
		
		//--------------- Country 2 ----------------------------------//
		City city4 = new City();
		city4.setName("Amsterdam");
		city4.setPopulation(821752L);
		city4.setCountry(countryRepo.findById(country2.getId()).get());
		cityRepo.saveAndFlush(city4);
		
		City city5 = new City();
		city5.setName("Roterdam");
		city5.setPopulation(623652L);
		city5.setCountry(countryRepo.findById(country2.getId()).get());
		cityRepo.saveAndFlush(city5);
		
		City city6 = new City();
		city6.setName("Delft");
		city6.setPopulation(101030L);
		city6.setCountry(countryRepo.findById(country2.getId()).get());
		cityRepo.saveAndFlush(city6);
		
		//--------------- Country 3 ----------------------------------//
		City city7 = new City();
		city7.setName("Canberra");
		city7.setPopulation(395790L);
		city7.setCountry(countryRepo.findById(country3.getId()).get());
		cityRepo.saveAndFlush(city7);
		
		City city8 = new City();
		city8.setName("Sidney");
		city8.setPopulation(4627000L);
		city8.setCountry(countryRepo.findById(country3.getId()).get());
		cityRepo.saveAndFlush(city8);
		
		City city9 = new City();
		city9.setName("Melbourne");
		city9.setPopulation(4443000L);
		city9.setCountry(countryRepo.findById(country3.getId()).get());
		cityRepo.saveAndFlush(city9);
		
		
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
		
		Hotel hotel3 = new Hotel();
		hotel3.setName("Masaya");
		hotel3.setAddress("Carrera 2 # 12-48 | La Candelaria, Bogotá, Cundinamarca");
		hotel3.setDescription("Ubicación Excelente en general para turismo, diversión y medios de transporte");
		hotel3.setCity(cityRepo.findById(city1.getId()).get());
		hotelRepo.saveAndFlush(hotel3);
		
		//----------------- Hotels Medellin --------------------------------//
		Hotel hotel4 = new Hotel();
		hotel4.setName("Dann Carlton");
		hotel4.setAddress("Cra 43A #7-50, Medellín, Antioquia");
		hotel4.setDescription("Ubicación Excelente en general para turismo, diversión y medios de transporte");
		hotel4.setCity(cityRepo.findById(city2.getId()).get());
		hotelRepo.saveAndFlush(hotel4);
		
		Hotel hotel5 = new Hotel();
		hotel5.setName("Suite 45");
		hotel5.setAddress("Cra. 45 #49-35, Medellín, Antioquia");
		hotel5.setDescription("Ubicación Excelente en general para turismo, diversión y medios de transporte");
		hotel5.setCity(cityRepo.findById(city2.getId()).get());
		hotelRepo.saveAndFlush(hotel2);
		
		Hotel hotel6 = new Hotel();
		hotel6.setName("Selina");
		hotel6.setAddress("Cra. 32d ##9 - 17, Medellín, Antioquia");
		hotel6.setDescription("Ubicación Excelente en general para turismo, diversión y medios de transporte");
		hotel6.setCity(cityRepo.findById(city2.getId()).get());
		hotelRepo.saveAndFlush(hotel6);
		
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
		
		Hotel hotel9 = new Hotel();
		hotel9.setName("Sarai");
		hotel9.setAddress("Calle 6 # 5-30, 190003 Popayán, Cauca");
		hotel9.setDescription("Ubicación Excelente en general para turismo, diversión y medios de transporte");
		hotel9.setCity(cityRepo.findById(city3.getId()).get());
		hotelRepo.saveAndFlush(hotel9);
		
		////////////////
		//Set Rooms
		////////////////
		
		
	}

}
