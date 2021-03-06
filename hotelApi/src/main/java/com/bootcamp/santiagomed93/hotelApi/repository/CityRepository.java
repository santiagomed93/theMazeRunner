package com.bootcamp.santiagomed93.hotelApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Country;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	List<City> findByCountry(Country country);
	City findByName(String name);
	List<City> findByNameContaining(String name);
	List<City> findByPopulationGreaterThanEqual(Long populationFrom);
	List<City> findByPopulationLessThanEqual(Long populationTo);
	List<City> findByPopulationBetween(Long populationFrom , Long populationTo);
	
}
