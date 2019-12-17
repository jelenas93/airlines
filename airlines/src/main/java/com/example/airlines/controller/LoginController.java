package com.example.airlines.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.airlines.dao.AdministratorDAO;
import com.example.airlines.dao.RoleDAO;
import com.example.airlines.dao.SupervizorDAO;
import com.example.airlines.dao.UserDAO;
import com.example.airlines.model.Administrator;
import com.example.airlines.model.Supervizor;
import com.example.airlines.model.User;
import com.example.airlines.model.Role;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	AdministratorDAO aDAO;
	
	@Autowired
	UserDAO uDAO;
	
	@Autowired
	SupervizorDAO sDAO;

	@PostMapping(value = "/loginUser")
	public ResponseEntity<String> userLogIn(@RequestBody User reqUser, HttpServletRequest request) {
		if (reqUser == null || reqUser.getUsername() == null || reqUser.getUsername().trim().equals("")
				|| reqUser.getPassword() == null || reqUser.getPassword().trim().equals("") ) {
			return new ResponseEntity<String>("Greska, Niste unijeli podatke", HttpStatus.BAD_REQUEST);
		}
		 User user=uDAO.findOneByUsername(reqUser.getUsername());
		 if(user == null) {
			return new ResponseEntity<String>("Ne postoji dati korisnik!", HttpStatus.FORBIDDEN);
		}
		try {
			request.login(reqUser.getUsername(), reqUser.getPassword());
			System.out.println("username "+reqUser.getUsername() +"password"+ reqUser.getPassword());
		} catch (Exception e) {
			return new ResponseEntity<String>("Vec ste ulogovani!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	@PostMapping(value = "/loginSupervizor")
	public ResponseEntity<String> supervizorLogIn(@RequestBody Supervizor reqUser, HttpServletRequest request) {
		if (reqUser == null || reqUser.getUsername() == null || reqUser.getUsername().trim().equals("")
				|| reqUser.getPassword() == null || reqUser.getPassword().trim().equals("") ) {
			return new ResponseEntity<String>("Greska, Niste unijeli podatke", HttpStatus.BAD_REQUEST);
		}
		 Supervizor supervizor=sDAO.findOneByUsername(reqUser.getUsername());
		 if(supervizor == null) {
			return new ResponseEntity<String>("Ne postoji dati supervizor!", HttpStatus.FORBIDDEN);
		}
		try {
			request.login(reqUser.getUsername(), reqUser.getPassword());
		} catch (Exception e) {
			return new ResponseEntity<String>("Vec ste ulogovani!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	@PostMapping(value = "/loginAdmin")
	public ResponseEntity<String> adnimLogIn(@RequestBody Administrator reqUser, HttpServletRequest request) {
		if (reqUser == null || reqUser.getUsername() == null || reqUser.getUsername().trim().equals("")
				|| reqUser.getPassword() == null || reqUser.getPassword().trim().equals("") ) {
			return new ResponseEntity<String>("Greska, Niste unijeli podatke", HttpStatus.BAD_REQUEST);
		}
		 Administrator admin=aDAO.findOneByUsername(reqUser.getUsername());
		 if(admin == null) {
			return new ResponseEntity<String>("Ne postoji dati administrator!", HttpStatus.FORBIDDEN);
		}
		try {
			request.login(reqUser.getUsername(), reqUser.getPassword());
		} catch (Exception e) {
			return new ResponseEntity<String>("Vec ste ulogovani!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@GetMapping(value = "/isAuthenticated")
	public ResponseEntity<String> userLogIn(HttpServletRequest request) {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated() == true) {
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("NO", HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(value = "/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		try {
			request.logout();
			return new ResponseEntity<String>("OK, uspjesno ste se odlogovali", HttpStatus.OK);
		} catch (ServletException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Greska", HttpStatus.FORBIDDEN);
		}
	}
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	com.example.airlines.dao.UserDAO userDAO;
	
	@Autowired
	SupervizorDAO supervizorDAO;
	
	@Autowired
	RoleDAO RoleDAO;
	
	@PostMapping(value = "/registerSupervizor")
	public ResponseEntity<String> supervizorRegister(@RequestBody Supervizor reqUser, HttpServletRequest request) {
		if (reqUser == null || reqUser.getPassword() == null || reqUser.getUsername() == null) {
			return new ResponseEntity<String>("Fail, Registration Data incomplete", HttpStatus.BAD_REQUEST);
		}
		Supervizor user = new Supervizor();
		user.setPassword(bCryptPasswordEncoder.encode(reqUser.getPassword()));
		user.setUsername(reqUser.getUsername());
		Role userRole = RoleDAO.findByRole("SUPERVIZOR");
		user.setRole(userRole);
		try {
			supervizorDAO.save(user);
		} catch (Exception e) {
			return new ResponseEntity<String>("User could not be saved!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("OK", HttpStatus.OK);

	}
}
