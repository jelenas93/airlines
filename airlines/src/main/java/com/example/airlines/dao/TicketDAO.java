package com.example.airlines.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Ticket;

public interface TicketDAO extends CrudRepository<Ticket, Long>{
	
	public ArrayList<Ticket> findAllByUser_Name(String name);
	
}
