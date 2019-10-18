package com.example.airlines.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.airlines.dao.AdministratorDAO;
import com.example.airlines.dao.SupervizorDAO;
import com.example.airlines.dao.UserDAO;
import com.example.airlines.model.Administrator;
import com.example.airlines.model.Supervizor;
import com.example.airlines.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	AdministratorDAO adminDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	SupervizorDAO supervizorDAO;

	@PostMapping(path = "/login"/* ,headers = { "content-type=application/json" } */)
	public ResponseEntity<String> userLogin(HttpServletRequest request) {
		System.out.println("Uslo u login");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("Prijava " + username + " password " + password);
		if ("".equals(username) || "".equals(password)) {
			System.out.println("Niste unijeli podatke");
			return new ResponseEntity<String>("Greska, nisu uneseni podaci.", HttpStatus.BAD_REQUEST);
		}

		User user = userDAO.findOneByUsernameAndPassword(username, password);
		Administrator admin = adminDAO.findOneByUsernameAndPassword(username, password);
		Supervizor supervizor = supervizorDAO.findOneByUsernameAndPassword(username, password);
		if (supervizor != null) {
			System.out.println("Supervizor");
			request.getSession().setAttribute("supervizor", supervizor);
		} else if (admin != null) {
			System.out.println("Admin");
			request.getSession().setAttribute("admin", admin);
		} else if (user != null) {
			System.out.println("User");
			request.getSession().setAttribute("user", user);
		} else {
			return new ResponseEntity<String>("Greska, korisnicko ime ili lozinka nisu ispravni.",
					HttpStatus.BAD_REQUEST);
		}
		/*
		 * try { request.login(username, password); } catch (Exception e) {
		 * e.printStackTrace(); return new
		 * ResponseEntity<String>("Greska, vec ste ulogovani.", HttpStatus.BAD_REQUEST);
		 * }
		 */

		return new ResponseEntity<String>("OK, uspjesno ste se prijavili na sistem.", HttpStatus.OK);
	}

}
