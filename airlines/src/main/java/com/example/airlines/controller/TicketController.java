package com.example.airlines.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlines.model.Ticket;
import com.example.airlines.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

	private TicketService ticketService;
	
	@Autowired
	public TicketController(TicketService ticketService) {
		this.ticketService=ticketService;
	}

	@GetMapping(value = "/{username}", produces = "application/json")
	public ResponseEntity<ArrayList<Ticket>> getAll(@PathVariable("username") String name, HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Ticket>>(ticketService.getAll(name), HttpStatus.OK);
	}

	@PostMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> save(@RequestBody Ticket ticket, HttpServletRequest request) {
		String response = ticketService.save(ticket);
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}
}
