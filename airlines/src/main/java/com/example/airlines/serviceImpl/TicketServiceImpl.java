package com.example.airlines.serviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlines.dao.FlightDAO;
import com.example.airlines.dao.TicketDAO;
import com.example.airlines.dao.UserDAO;
import com.example.airlines.dto.TicketDTO;
import com.example.airlines.model.Flight;
import com.example.airlines.model.Ticket;
import com.example.airlines.model.User;
import com.example.airlines.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDAO ticketDAO;

	@Autowired
	FlightDAO flightDAO;

	@Autowired
	UserDAO userDAO;

	@Override
	public ArrayList<Ticket> getAll(String name) {
		return ticketDAO.findAllByUser_Username(name);
	}

	@Override
	public String save(TicketDTO recObj) {
		if (recObj.getFlight() == null || recObj.getUser() == null || "".equals(recObj.getNumberOfTicket() + "")) {
			return "Greska, nisu uneseni svi podaci.";
		}
		Optional<Flight> flight = flightDAO.findById(recObj.getFlight().getId());
		User user = userDAO.findOneByUsername(recObj.getUser().getUsername());
		if (flight == null || user == null)
			return "Greska, ne postoji dati let.";
		int ukupnoMjesta = recObj.getNumberOfTicket() + flight.get().getSeatReserved();
		if (ukupnoMjesta > flight.get().getAirplane().getSeats())
			return "Greska, nema dovoljno mjesta.";
		flight.get().setSeatReserved(ukupnoMjesta);
		// try {
		flightDAO.save(flight.get());
		/*
		 * } catch (IllegalArgumentException ex1) { return
		 * "Exception in Flight Controller POST (ex1), contact admins!"; } catch
		 * (Exception ex2) { return
		 * "Exception in Flight Controller POST (ex2), contact admins!"; }
		 */

		Ticket ticket = new Ticket(user, recObj.getNumberOfTicket(), flight.get());
		try {
			ticketDAO.save(ticket);
		} catch (IllegalArgumentException ex1) {
			return "Exception in Ticket Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in Ticket Controller POST (ex2), contact admins!";
		}

		return "OK, karta uspjesno kupljena.";
	}

}
