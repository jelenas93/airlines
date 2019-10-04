package com.example.airlines.dto;

public class AirplaneDTO {

	private Long id;

	private int seats;

	private String brand;
	
	private boolean isActive;
	
	
	public AirplaneDTO() {
		super();
	}

	public AirplaneDTO(int seats, String brand, boolean isActive) {
		super();
		this.seats = seats;
		this.brand = brand;
		this.isActive=isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
