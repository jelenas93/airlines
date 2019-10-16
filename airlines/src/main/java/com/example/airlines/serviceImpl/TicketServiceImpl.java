package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlines.dao.FlightDAO;
import com.example.airlines.dao.TicketDAO;
import com.example.airlines.dao.UserDAO;
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
	public String save(Ticket recObj) {
		if (recObj.getFlight() == null || recObj.getUser() == null || "".equals(recObj.getNumberOfTicket() + "")
				|| recObj.getNumberOfTicket() <= 0) {
			return "Greska, nisu uneseni svi podaci.";
		}
		Ticket ticket = (ticketDAO.findById(recObj.getId())).get();

		if (ticket != null) {
			return "Greska, vec postoji kupljena karta.";
		}
		Flight flight = (flightDAO.findById(recObj.getFlight().getId())).get();
		if (flight == null)
			return "Greska, ne postoji dati let.";
		User user = userDAO.findOneByUsername(recObj.getUser().getUsername());
		if (user == null) {
			return "Greska, ne postoji dati korisnik.";
		}

		int ukupnoMjesta = recObj.getNumberOfTicket() + flight.getSeatReserved();
		if (ukupnoMjesta > flight.getAirplane().getSeats())
			return "Greska, nema dovoljno mjesta.";
		flight.setSeatReserved(ukupnoMjesta);
		try {
			flightDAO.save(flight);
		} catch (IllegalArgumentException ex1) {
			return "Exception in Ticket Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in Ticket Controller POST (ex2), contact admins!";
		}

		ticket = new Ticket(user, recObj.getNumberOfTicket(), flight);
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
