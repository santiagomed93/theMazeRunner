package com.bootcamp.santiagomed93.hotelApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.santiagomed93.hotelApi.model.Hotel;
import com.bootcamp.santiagomed93.hotelApi.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room , Long>{

	List<Room> findByHotel(Hotel hotel);
	List<Room> findByCostGreaterThanEqual(Integer costFrom);
	List<Room> findByCostGreaterThanEqualAndCapacity(Integer costFrom, Integer capacity);
	List<Room> findByCostLessThanEqual(Integer costTo);
	List<Room> findByCostLessThanEqualAndCapacity(Integer costTo, Integer capacity);
	List<Room> findByCostBetween(Integer costFrom, Integer costTo);
	List<Room> findByCostBetweenAndCapacity(Integer costFrom, Integer costTo, Integer capacity);
}
