package com.bootcamp.santiagomed93.hotelApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.santiagomed93.hotelApi.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{

	Country findByName(String name);
	
}
