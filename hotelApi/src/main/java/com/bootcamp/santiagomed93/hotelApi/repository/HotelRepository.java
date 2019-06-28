package com.bootcamp.santiagomed93.hotelApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.santiagomed93.hotelApi.model.City;
import com.bootcamp.santiagomed93.hotelApi.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

	List<Hotel> findByCity(City city);
	Hotel findByName(String name);
	List<Hotel> findByNameContaining(String name);
}
