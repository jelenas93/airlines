package com.example.airlines.controller;

import java.util.ArrayList;

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

import com.example.airlines.dao.AdministratorDAO;
import com.example.airlines.dao.FlightDAO;
import com.example.airlines.dao.SupervizorDAO;
import com.example.airlines.model.Administrator;
import com.example.airlines.model.Flight;
import com.example.airlines.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

	@Autowired
	AdministratorDAO adminDAO;

	@Autowired
	SupervizorDAO supervizorDAO;

	@Autowired
	FlightDAO flightDAO;

	@Autowired
	FlightService flightService;

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Flight> getOne(@PathVariable("id") Long id, HttpServletRequest request) {
	//	if (request.getSession().getAttribute("supervizor") != null) {
			return new ResponseEntity<Flight>(flightService.getOne(id), HttpStatus.OK);
	/*	} else {
			return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
		}*/
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<ArrayList<Flight>> getAll(HttpServletRequest request) {
	//	if (request.getSession().getAttribute("supervizor") != null) {
			return new ResponseEntity<ArrayList<Flight>>(flightService.getAll(), HttpStatus.OK);
	/*	} else {
			return new ResponseEntity<ArrayList<Flight>>(HttpStatus.BAD_REQUEST);
		}*/
	}

	@GetMapping(path = "/aktivni", produces = "application/json")
	public ResponseEntity<ArrayList<Flight>> getAllActive(HttpServletRequest request) {
	//	if (request.getSession().getAttribute("supervizor") != null) {
			return new ResponseEntity<ArrayList<Flight>>(flightService.getAllActive(), HttpStatus.OK);
	/*	} else {
			return new ResponseEntity<ArrayList<Flight>>(flightService.getAll(), HttpStatus.BAD_REQUEST);
		}*/
	}

	@PostMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> save(@RequestBody Flight flight, HttpServletRequest request) {
		String response;
	/*	if (request.getSession().getAttribute("supervizor") != null
				|| (request.getSession().getAttribute("admin") != null
						&& ((Administrator) request.getSession().getAttribute("admin")).getAirCompany()
								.getId() == flight.getAirCompany().getId())) {*/
			response = flightService.save(flight);
/*		} else {
			response = "Greska";
		}*/
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
/*		if (request.getSession().getAttribute("supervizor") != null
				|| (request.getSession().getAttribute("admin") != null
						&& ((Administrator) request.getSession().getAttribute("admin")).getAirCompany()
								.getId() == flight.getAirCompany().getId())) {*/
			response = flightService.edit(flight);
	/*	} else
			response = "Greska";*/
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
	//	Flight flight = flightDAO.findOneById(id);
	/*	if (request.getSession().getAttribute("supervizor") != null
				|| (request.getSession().getAttribute("admin") != null
						&& ((Administrator) request.getSession().getAttribute("admin")).getAirCompany()
								.getId() == flight.getAirCompany().getId())) {*/
			response = flightService.notActive(id);
	/*	} else {
			response = "Greska";
		}*/
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}
}
