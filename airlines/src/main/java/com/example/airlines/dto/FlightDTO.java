package com.example.airlines.dto;

import java.util.Date;

import com.example.airlines.model.AirCompany;
import com.example.airlines.model.Airplane;
import com.example.airlines.model.Destination;

public class FlightDTO {

	public Long id;

	public Airplane airplane;

	public int seatReserved;

	public Destination destination;

	public AirCompany airCompany;

	public Date flightDate;

	public Double price;

	public boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public int getSeatReserved() {
		return seatReserved;
	}

	public void setSeatReserved(int seatReserved) {
		this.seatReserved = seatReserved;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public AirCompany getAirCompany() {
		return airCompany;
	}

	public void setAirCompany(AirCompany airCompany) {
		this.airCompany = airCompany;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}