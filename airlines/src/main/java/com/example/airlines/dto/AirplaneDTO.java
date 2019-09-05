package com.example.airlines.dto;

public class AirplaneDTO {

	public Long id;

	public int seats;

	public String brand;
	public AirplaneDTO() {
		super();
	}

	public AirplaneDTO(int seats, String brand) {
		super();
		this.seats = seats;
		this.brand = brand;
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

}
