package com.bootcamp.santiagomed93.vagoApi.model;


public class Room {
	
	private Long id;
	private String numberRoom;
	private Integer singleBed;
	private Integer doubleBed;
	private Integer capacity;
	private Integer cost;
	
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
}
