package com.bootcamp.santiagomed93.vagoApi.model;

import java.util.List;

public class Country {

	private Long id;
	private String name;
	private List<City> cities;
	
	public Country() {
		
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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	
}
