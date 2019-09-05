package com.example.airlines.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Flight {

	@Id
	public Long id;

	@OneToOne
	@JoinColumn(name = "airplane", referencedColumnName = "id", nullable = false)
	public Airplane airplane;

	@Column(nullable = false)
	public int seatReserved;

	@ManyToOne
	@JoinColumn(name = "destination", referencedColumnName = "id", nullable = false)
	public Destination destination;

	@OneToOne
	@JoinColumn(name = "airCompany", referencedColumnName = "id", nullable = false)
	public AirCompany airCompany;

	@Column(nullable = false)
	public Date flightDate;

	@Column(nullable = false)
	public Double price;

	@Column(nullable = false)
	public boolean isActive;

	public Flight() {
		super();
	}

	public Flight(Airplane airplane, int seatReserved, Destination destination, AirCompany airCompany, Date flightDate,
			Double price, boolean isActive) {
		super();
		this.airplane = airplane;
		this.seatReserved = seatReserved;
		this.destination = destination;
		this.airCompany = airCompany;
		this.flightDate = flightDate;
		this.price = price;
		this.isActive = isActive;
	}

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
