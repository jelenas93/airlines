package com.example.airlines.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlines.model.Flight;
import com.example.airlines.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

	private FlightService flightService;

	@Autowired
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Flight> getOne(@PathVariable("id") Long id, HttpServletRequest request) {
		return new ResponseEntity<Flight>(flightService.getOne(id), HttpStatus.OK);
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<ArrayList<Flight>> getAll(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Flight>>(flightService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/destination/{name}", produces = "application/json")
	public ResponseEntity<ArrayList<Flight>> getAllByDestinationName(@PathVariable("name") String name, HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Flight>>(flightService.getAllByDestination_Name(name), HttpStatus.OK);
	}
	@GetMapping(value = "/airCompany/{id}", produces = "application/json")
	public ResponseEntity<ArrayList<Flight>> getAllByAirCompanyId(@PathVariable("id") Long id, HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Flight>>(flightService.getAllByAirCompany_Id(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/aktivni", produces = "application/json")
	public ResponseEntity<ArrayList<Flight>> getAllActive(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Flight>>(flightService.getAllActive(), HttpStatus.OK);
	}

	@PostMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> save(@RequestBody Flight flight, HttpServletRequest request) {
		String response;
		response = flightService.save(flight);
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}

	@PutMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> edit(@RequestBody Flight flight, HttpServletRequest request) {
		String response;
		response = flightService.edit(flight);
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/{id}", headers = { "content-type=application/json" })
	public ResponseEntity<String> notActive(@PathVariable("id") Long id, HttpServletRequest request) {
		String response;
		response = flightService.notActive(id);
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}
}
