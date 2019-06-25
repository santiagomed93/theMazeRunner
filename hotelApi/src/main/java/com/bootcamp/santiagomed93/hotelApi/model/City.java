package com.bootcamp.santiagomed93.hotelApi.model;

import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="CITY")
public class City {
	
	@Id
	//Esta contante es para mySQL
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", length=250, nullable=false)
	private String name;
	
	@Column(name="population")
	private Long population;
	
	@OneToMany(mappedBy = "city",fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Hotel> hoteles;
	
	@ManyToOne
	@JoinColumn(name="idCountry")
	@JsonBackReference
	private Country country;

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

	public List<Hotel> getHoteles() {
		return hoteles;
	}

	public void setHoteles(List<Hotel> hoteles) {
		this.hoteles = hoteles;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", population=" + population + ", hoteles=" + hoteles
				+ ", country=" + country + "]";
	}

	
}
