package com.example.airlines.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Ticket {

	@Id
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
	private User user;
	
	@Column(nullable = false)
	private int numberOfTicket;
	
	@OneToOne
	@JoinColumn(name = "flight", referencedColumnName = "id", nullable = false)
	private Flight flight;
	
	public Ticket() {
		super();
	}

	public Ticket(User user, int numberOfTicket, Flight flight) {
		super();
		this.user = user;
		this.numberOfTicket = numberOfTicket;
		this.flight = flight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumberOfTicket() {
		return numberOfTicket;
	}

	public void setNumberOfTicket(int numberOfTicket) {
		this.numberOfTicket = numberOfTicket;
	}
	
	
}
