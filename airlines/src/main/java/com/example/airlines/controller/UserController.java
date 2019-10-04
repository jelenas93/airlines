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
		return new ResponseEntity<ArrayList<User>>(userService.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/{username}", headers = { "content-type=application/json" })
	// @GetMapping(value = "/{username}", produces="aplication/json")
	public ResponseEntity<User> getOne(@PathVariable("username") String name, HttpServletRequest request) {
		return new ResponseEntity<User>(userService.getOne(name), HttpStatus.OK);
	}

	@PostMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> save(@RequestBody User user, HttpServletRequest request) {
		String response = userService.save(user);
		if (response.contains("Greska")) {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} else if (response.contains("Exception")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/{username}", headers = { "content-type=application/json" })
	public ResponseEntity<String> flagNotActive(@PathVariable("username") String name, HttpServletRequest request) {
		String recStr = userService.notActive(name);
		if (recStr.contains("Greska")) {
			return new ResponseEntity<String>(recStr, HttpStatus.BAD_REQUEST);
		} else if (recStr.contains("Exception")) {
			return new ResponseEntity<String>(recStr, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(recStr, HttpStatus.ACCEPTED);
		}
	}
}
