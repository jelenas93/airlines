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

import com.example.airlines.dao.FlightDAO;
import com.example.airlines.model.Flight;
import com.example.airlines.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
	
	@Autowired
	FlightDAO flightDAO;
	
	@Autowired
	FlightService flightService;
	
	@GetMapping(value ="/{id}", produces="application/json")
	public ResponseEntity<Flight> get(@PathVariable("id") Long id, HttpServletRequest request){
		return new ResponseEntity<Flight>(flightService.get(id),HttpStatus.OK);
	}
	
	@GetMapping(produces="application/json")
	public ResponseEntity<ArrayList<Flight>> getAll(HttpServletRequest request){
		return new ResponseEntity<ArrayList<Flight>>(flightService.getAll(),HttpStatus.OK);
	}
	
	@PostMapping(path="/save",produces="application/json")
	public ResponseEntity<String> save(@RequestBody Flight flight, HttpServletRequest request) {
		String response = flightService.save(flight);
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path="/edit",produces="application/json")
	public ResponseEntity<String> edit(@RequestBody Flight flight, HttpServletRequest request) {
		String response = flightService.edit(flight);
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}
	
	@PostMapping( value="/{id}", produces="application/json")
	public ResponseEntity<String> notActive(@PathVariable("id") Long id, HttpServletRequest request) {
		String response = flightService.notActive(id);
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}
}