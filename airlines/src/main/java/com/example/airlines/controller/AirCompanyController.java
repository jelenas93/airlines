package com.example.airlines.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlines.dao.AirCompanyDAO;
import com.example.airlines.model.AirCompany;
import com.example.airlines.service.AirCompanyService;

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

@RestController
@RequestMapping("/api/airCompany")
public class AirCompanyController {

	@Autowired
	AirCompanyDAO airCompanyDAO;
	@Autowired
	AirCompanyService airCompanyService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<ArrayList<AirCompany>> getAll(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<AirCompany>>(airCompanyService.getAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/aktivni", produces = "application/json")
	public ResponseEntity<ArrayList<AirCompany>> getAllActive(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<AirCompany>>(airCompanyService.getAllActive(), HttpStatus.OK);
	}

	@GetMapping(value = "/{name}", produces = "application/json")
	public ResponseEntity<AirCompany> getOne(@PathVariable("name") String name, HttpServletRequest request) {

		return new ResponseEntity<AirCompany>(airCompanyService.getOne(name), HttpStatus.OK);
	}

	@PostMapping(headers = { "content-type=application/json" })
	public ResponseEntity<String> save(@RequestBody AirCompany airCompany, HttpServletRequest request) {
		String recStr;
		if(request.getSession().getAttribute("supervizor")!=null) {
			recStr = airCompanyService.save(airCompany);
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
	public ResponseEntity<String> edit(@RequestBody AirCompany airCompany, HttpServletRequest request) {
		String recStr;
		if(request.getSession().getAttribute("supervizor")!=null) {
			recStr = airCompanyService.edit(airCompany);
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
			recStr = airCompanyService.notActive(name);
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
