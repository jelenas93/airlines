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

	/*@PostMapping(path = "/login")
	public ResponseEntity<String> userLogin(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ("".equals(username) || "".equals(password)) {
			return new ResponseEntity<String>("Greska, nisu uneseni podaci.", HttpStatus.BAD_REQUEST);
		}
		User user = userDAO.findOneByUsernameAndPassword(username, password);
		Administrator admin = adminDAO.findOneByUsernameAndPassword(username, password);
		Supervizor supervizor = supervizorDAO.findOneByUsernameAndPassword(username, password);
		if (supervizor != null) {
			request.getSession().setAttribute("supervizor", supervizor);
		} else if (admin != null) {
			if (admin.getActive())
				request.getSession().setAttribute("admin", admin);
			else
				return new ResponseEntity<String>("Greska, administrator je suspendovan.", HttpStatus.BAD_REQUEST);

		} else if (user != null) {
			if (user.getActive())
				request.getSession().setAttribute("user", user);
			else
				return new ResponseEntity<String>("Greska, korisnik je suspendovan.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>("Greska, korisnicko ime ili lozinka nisu ispravni.",
					HttpStatus.BAD_REQUEST);
		}
		System.out.println("Sesija "+request.getSession().getId());
		return new ResponseEntity<String>("OK, uspjesno ste se prijavili na sistem.", HttpStatus.OK);
	}
*/
}
