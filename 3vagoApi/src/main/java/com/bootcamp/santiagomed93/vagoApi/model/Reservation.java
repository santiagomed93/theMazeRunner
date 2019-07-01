package com.bootcamp.santiagomed93.vagoApi.model;

import java.time.LocalDate;

public class Reservation {

	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reservationCode;
	private Long totalCost;
	private boolean paid;
	
	public Reservation() {
		this.paid = false;
	}
	
	public Reservation(Long id, LocalDate startDate, LocalDate endDate) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	public Long getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", reservationCode="
				+ reservationCode + ", totalCost=" + totalCost + ", paid=" + paid + "]";
	}
	
}
