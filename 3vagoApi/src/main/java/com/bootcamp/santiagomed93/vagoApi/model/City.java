package com.bootcamp.santiagomed93.vagoApi.model;

import java.util.List;


public class City {
	
	private Long id;
	private String name;
	private Long population;
	private List<Hotel> hotels;

	public City() {}
	
	public City (Long id, String name, Long population) {
		this.id = id;
		this.name = name;
		this.population = population;
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

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	
}
