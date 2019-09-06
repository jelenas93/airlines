package com.example.airlines.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Airplane {

	@Id
	private Long id;
	
	@Column(nullable = false)
	private int seats;
	
	@Column(nullable = false)
	private String brand;

	public Airplane() {
		super();
	}

	public Airplane(int seats, String brand) {
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
