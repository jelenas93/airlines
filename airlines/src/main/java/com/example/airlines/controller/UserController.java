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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlines.dao.UserDAO;
import com.example.airlines.model.User;
import com.example.airlines.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserDAO userDAO;

	@Autowired
	public UserService userService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<ArrayList<User>> getAll(HttpServletRequest request) {
	//	if (request.getSession().getAttribute("supervizor") != null) {
			return new ResponseEntity<ArrayList<User>>(userService.getAll(), HttpStatus.OK);
	/*	} else {
			return new ResponseEntity<ArrayList<User>>(HttpStatus.BAD_REQUEST);
		}*/
	}

	@GetMapping(path = "/aktivni", produces = "application/json")
	public ResponseEntity<ArrayList<User>> getAllActive(HttpServletRequest request) {
		if (request.getSession().getAttribute("supervizor") != null) {
			return new ResponseEntity<ArrayList<User>>(userService.getAllActive(), HttpStatus.OK);
		} else {
			return new ResponseEntity<ArrayList<User>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{username}", headers = { "content-type=application/json" })
	public ResponseEntity<User> getOne(@PathVariable("username") String name, HttpServletRequest request) {
		if (request.getSession().getAttribute("supervizor") != null) {
			return new ResponseEntity<User>(userService.getOne(name), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> save(@RequestBody User user, HttpServletRequest request) {
		String response;
	//	if (request.getSession().getAttribute("supervizor") != null) { //supervizor samo za testiranje
			response = userService.save(user);
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

	@DeleteMapping(value = "/{username}", headers = { "content-type=application/json" })
	public ResponseEntity<String> flagNotActive(@PathVariable("username") String name, HttpServletRequest request) {
		String response;
		if (request.getSession().getAttribute("supervizor") != null) {
			response = userService.notActive(name);
		} else {
			response = "Greska";
		}
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
		}
	}
}
