package com.example.airlines.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlines.dao.DestinationDAO;
import com.example.airlines.model.Destination;
import com.example.airlines.service.DestinationService;

@RestController
@RequestMapping("/api/destination")
public class DestinationController {

	@Autowired
	DestinationDAO destinationDAO;
	@Autowired
	DestinationService destinationService;
	

	@GetMapping(path="/all", produces = "application/json")
	public ResponseEntity<ArrayList<Destination>> getAll() {
		return new ResponseEntity<ArrayList<Destination>>(destinationService.getAll(), HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{name}", produces="application/json")
	public ResponseEntity<Destination> getOne(@PathVariable("name") String username, HttpRequest request){
		
		return new ResponseEntity<Destination>(destinationService.getOne(username), HttpStatus.OK);
	}
	@PostMapping(headers="content-type=application/json")
	public ResponseEntity<String> save(@RequestBody Destination admin, HttpServletRequest request){
		String recStr = destinationService.save(admin);
		if (recStr.contains("Fail")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}
	
	@PutMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> edit(@RequestBody Destination admin, HttpServletRequest request) {
		String recStr = destinationService.edit(admin);
		if (recStr.contains("Fail")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}
	
	
}
