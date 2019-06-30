package com.bootcamp.santiagomed93.hotelApi.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ROOM")
public class Room {
	
	@Id
	//Esta contante es para mySQL
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="numberRoom", length=250, nullable=false)
	private String numberRoom;
	
	@Column(name="singleBed")
	@NotNull
	private Integer singleBed;
	
	@Column(name="doubleBed")
	@NotNull
	private Integer doubleBed;
	
	@Column(name="capacity")
	private Integer capacity;
	
	@Column(name="cost")
	@NotNull
	private Integer cost;
	
	@ManyToOne
	@JoinColumn(name="idHotel")
	@JsonBackReference
	private Hotel hotel;
	
	@OneToMany(mappedBy = "room",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Reservation> reservations;
	
	
	public Room() {}
	
	public Room(Long id, String numberRoom, Integer singleBed, Integer doubleBed, Integer cost) {
		this.id = id;
		this.numberRoom = numberRoom;
		this.singleBed = singleBed;
		this.doubleBed = doubleBed;
		this.cost = cost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumberRoom() {
		return numberRoom;
	}

	public void setNumberRoom(String numberRoom) {
		this.numberRoom = numberRoom;
	}

	public Integer getSingleBed() {
		return singleBed;
	}

	public void setSingleBed(Integer singleBed) {
		this.singleBed = singleBed;
	}

	public Integer getDoubleBed() {
		return doubleBed;
	}

	public void setDoubleBed(Integer doubleBed) {
		this.doubleBed = doubleBed;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

}
