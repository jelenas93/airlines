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
import com.example.airlines.model.Administrator;
import com.example.airlines.service.AdministratorService;

@RestController
@RequestMapping("/api/administrator")
public class AdministratorController {

	@Autowired
	AdministratorDAO adminDAO;
	@Autowired
	AdministratorService adminService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<ArrayList<Administrator>> getAll(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Administrator>>(adminService.getAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/aktivni", produces = "application/json")
	public ResponseEntity<ArrayList<Administrator>> getAllActive(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Administrator>>(adminService.getAllActive(), HttpStatus.OK);
	}

	@GetMapping(value = "/{username}", headers = { "content-type=application/json" })
	public ResponseEntity<Administrator> getOne(@PathVariable("username") String username, HttpServletRequest request) {
		return new ResponseEntity<Administrator>(adminService.getOne(username), HttpStatus.OK);
	}

	@PostMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> save(@RequestBody Administrator admin, HttpServletRequest request) {
		String recStr = adminService.save(admin);
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}

	@PutMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> edit(@RequestBody Administrator admin, HttpServletRequest request) {
		String recStr = adminService.edit(admin);
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(value = "/{username}", headers = { "content-type=application/json" })
	public ResponseEntity<String> notActive(@PathVariable("username") String username, HttpServletRequest request) {
		String recStr = adminService.notActive(username);
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}
}
