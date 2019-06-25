package com.bootcamp.santiagomed93.hotelApi.model.request;

public class HotelRequest {

	private String name;
	private String address;
	private String description;
	
	public HotelRequest() {}
	
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
}
