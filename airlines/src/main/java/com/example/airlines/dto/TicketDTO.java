package com.example.airlines.dto;

import com.example.airlines.model.Flight;
import com.example.airlines.model.User;

public class TicketDTO {

	private Long id;

	private User user;

	private int numberOfTicket;

	private Flight flight;
	
	public TicketDTO() {
		super();
	}

	public TicketDTO(User user, int numberOfTicket, Flight flight) {
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
