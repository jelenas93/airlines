package com.example.airlines.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping(produces = "aplication/json")
	public ResponseEntity<ArrayList<Administrator>> getAll(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<Administrator>>(adminService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value="/{username}", produces="aplication/json")
	public ResponseEntity<Administrator> getOne(@PathVariable("username") String username, HttpRequest request){
		
		return new ResponseEntity<Administrator>(adminService.getOne(username), HttpStatus.OK);
	}
	@PostMapping(headers="content-type=aplication/json")
	public ResponseEntity<String> save(@RequestBody Administrator admin, HttpServletRequest request){
		String recStr = adminService.save(admin);
		if (recStr.contains("Fail")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}
}
