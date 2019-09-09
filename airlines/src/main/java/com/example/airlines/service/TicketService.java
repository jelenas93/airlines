package com.example.airlines.service;

import java.util.ArrayList;

import com.example.airlines.dto.TicketDTO;
import com.example.airlines.model.Ticket;

public interface TicketService{
	
	public ArrayList<Ticket> getAll(String name);

	public String save(TicketDTO recObj);

}
