package com.bootcamp.santiagomed93.hotelApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="HOTEL")
public class Hotel {
	
	@Id
	//Esta contante es para mySQL
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", length=250, nullable=false)
	private String name;
	
	@Column(name="address", length=250, nullable=false)
	private String address;
	
	@Column(name="description", length=250)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="idCity")
	@JsonBackReference
	private City city;

	
	public Hotel() {}
	
	public Hotel(Long id, String name, String address, String description) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	

}
