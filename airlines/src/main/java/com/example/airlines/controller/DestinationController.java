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

	@GetMapping(produces = "application/json")
	public ResponseEntity<ArrayList<Destination>> getAll(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Destination>>(destinationService.getAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/aktivni", produces = "application/json")
	public ResponseEntity<ArrayList<Destination>> getAllActive(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Destination>>(destinationService.getAllActive(), HttpStatus.OK);
	}

	@GetMapping(value = "/{name}", headers = { "content-type=application/json" })
	public ResponseEntity<Destination> getOne(@PathVariable("name") String username, HttpServletRequest request) {
		return new ResponseEntity<Destination>(destinationService.getOne(username), HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<String> save(@RequestBody Destination destination, HttpServletRequest request) {
		String recStr;
		if(request.getSession().getAttribute("admin")!=null || request.getSession().getAttribute("supervizor")!=null) {
		 recStr = destinationService.save(destination);
		 
		}else {
			recStr="Greska";
		}
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}

	@PutMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> edit(@RequestBody Destination destination, HttpServletRequest request) {
		String recStr;
		if(request.getSession().getAttribute("supervizor")!=null) {
	    recStr = destinationService.edit(destination);
		}else {
			recStr="Greska";
		}
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(value = "/{name}", headers = { "content-type=application/json" })
	public ResponseEntity<String> notActive(@PathVariable("name") String name, HttpServletRequest request) {
		
		String recStr;
		if(request.getSession().getAttribute("supervizor")!=null) {
			recStr = destinationService.notActive(name);
		}else {
			recStr="Greska";
		}
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}

}
