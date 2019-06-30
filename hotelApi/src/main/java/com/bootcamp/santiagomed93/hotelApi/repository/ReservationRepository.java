package com.bootcamp.santiagomed93.hotelApi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.santiagomed93.hotelApi.model.Reservation;
import com.bootcamp.santiagomed93.hotelApi.model.Room;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByRoom(Room room);
	List<Reservation> findByStartDateBetweenOrEndDateBetween(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2);
}
