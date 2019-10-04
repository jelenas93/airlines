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


import com.example.airlines.dao.AirplaneDAO;
import com.example.airlines.model.Airplane;
import com.example.airlines.service.AirplaneService;


@RestController
@RequestMapping("/api/airplane")
public class AirplaneController {


	@Autowired
	AirplaneDAO airplaneDAO;
	@Autowired
	AirplaneService airplaneService;
	

	@GetMapping( produces = "application/json")
	public ResponseEntity<ArrayList<Airplane>> getAll() {
		return new ResponseEntity<ArrayList<Airplane>>(airplaneService.getAll(), HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{id}", produces="application/json")
	public ResponseEntity<Airplane> getOne(@PathVariable("id") Long id, HttpServletRequest request){
		
		return new ResponseEntity<Airplane>(airplaneService.getOneById(id), HttpStatus.OK);
	}
	@PostMapping( headers = { "content-type=application/json" })
	public ResponseEntity<String> save(@RequestBody Airplane airplane, HttpServletRequest request){
		String recStr = airplaneService.save(airplane);
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}
	
	@PutMapping( headers = { "content-type=application/json" })
	public ResponseEntity<String> edit(@RequestBody Airplane airplane, HttpServletRequest request) {
		String recStr = airplaneService.edit(airplane);
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}
	@DeleteMapping(value = "/{id}", headers = { "content-type=application/json" })
	public ResponseEntity<String> flagNotActive(@PathVariable("id") Long id, HttpServletRequest request) {
		String recStr = airplaneService.notActive(id);
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}
}
