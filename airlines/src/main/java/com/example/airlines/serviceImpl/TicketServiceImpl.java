package com.example.airlines.serviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.airlines.dao.FlightDAO;
import com.example.airlines.dao.TicketDAO;
import com.example.airlines.dao.UserDAO;
import com.example.airlines.dto.TicketDTO;
import com.example.airlines.model.Flight;
import com.example.airlines.model.Ticket;
import com.example.airlines.model.User;
import com.example.airlines.service.TicketService;

public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDAO ticketDAO;

	@Autowired
	FlightDAO flightDAO;

	@Autowired
	UserDAO userDAO;

	@Override
	public ArrayList<Ticket> getAll(String name) {
		return ticketDAO.findAllByUser_Name(name);
	}

	@Override
	public String save(TicketDTO recObj) {
		if (recObj.getFlight() == null || recObj.getUser() == null || "".equals(recObj.getNumberOfTicket()+"")) {
			return "Greska, nisu uneseni svi podaci.";
		}
		Optional<Flight> flight = flightDAO.findById(recObj.getFlight().getId());
		User user = userDAO.findOneByName(recObj.getUser().getUsername());
		if (flight == null || user == null)
			return "Greska, ne postoji dati let.";
		int ukupnoMjesta = recObj.getNumberOfTicket() + flight.get().getSeatReserved();
		if (ukupnoMjesta > flight.get().getAirplane().getSeats())
			return "Greska, nema dovoljno mjesta.";
		flight.get().setSeatReserved(ukupnoMjesta);
		flightDAO.save(flight.get());

		Ticket ticket = new Ticket(user, recObj.getNumberOfTicket(), flight.get());
		ticketDAO.save(ticket);

		return "OK, karta uspjesno kupljena.";
	}

}
