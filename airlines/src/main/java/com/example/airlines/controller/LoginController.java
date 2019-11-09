package com.example.airlines.controller;


import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.airlines.dao.RoleDAO;
import com.example.airlines.dao.SupervizorDAO;
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
	
	public static boolean user=false;
	public static boolean admin=false;
	public static boolean supervizor=false;
	

	@PostMapping(value = "/loginUser")
	public ResponseEntity<String> userLogIn(@RequestBody User reqUser, HttpServletRequest request) {
		System.out.println("Uslo login user 1");
		if (reqUser == null || reqUser.getUsername() == null || reqUser.getUsername().trim().equals("")
				|| reqUser.getPassword() == null || reqUser.getPassword().trim().equals("") ) {
			
			return new ResponseEntity<String>("Greska, Niste unijeli podatke", HttpStatus.BAD_REQUEST);
		}
		try {
			
			request.login(reqUser.getUsername(), reqUser.getPassword());
			System.out.println("username "+reqUser.getUsername() +"password"+ reqUser.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Vec ste ulogovani!", HttpStatus.BAD_REQUEST);
		}
		System.out.println("Uslo login user");
		user=true;
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	@PostMapping(value = "/loginSupervizor")
	public ResponseEntity<String> supervizorLogIn(@RequestBody Supervizor reqUser, HttpServletRequest request) {
		//System.out.println("Uslo login supervizor");
		if (reqUser == null || reqUser.getUsername() == null || reqUser.getUsername().trim().equals("")
				|| reqUser.getPassword() == null || reqUser.getPassword().trim().equals("") ) {
			return new ResponseEntity<String>("Greska, Niste unijeli podatke", HttpStatus.BAD_REQUEST);
		}
		try {
			request.login(reqUser.getUsername(), reqUser.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Vec ste ulogovani!", HttpStatus.BAD_REQUEST);
		}
		supervizor=true;
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	@PostMapping(value = "/loginAdmin")
	public ResponseEntity<String> adnimLogIn(@RequestBody Administrator reqUser, HttpServletRequest request) {
		System.out.println("Uslo vuj");
		if (reqUser == null || reqUser.getUsername() == null || reqUser.getUsername().trim().equals("")
				|| reqUser.getPassword() == null || reqUser.getPassword().trim().equals("") ) {
			return new ResponseEntity<String>("Greska, Niste unijeli podatke", HttpStatus.BAD_REQUEST);
		}
		try {
			request.login(reqUser.getUsername(), reqUser.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Vec ste ulogovani!", HttpStatus.BAD_REQUEST);
		}
		admin=true;
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@GetMapping(value = "/isAuthenticated")
	public ResponseEntity<String> userLogIn(HttpServletRequest request) {
		System.out.println("Uslo u autentikaciju");
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated() == true) {
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("NO", HttpStatus.FORBIDDEN);
		}
	}
	
	/*@GetMapping(value = "/isAuthenticatedSupervizor")
	public ResponseEntity<String> supervizorLogIn(HttpServletRequest request) {
		System.out.println("Uslo vuj");
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated() == true) {
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("NO", HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping(value = "/isAuthenticatedAdmin")
	public ResponseEntity<String> adminLogIn(HttpServletRequest request) {
		System.out.println("Uslo vuj");
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated() == true) {
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("NO", HttpStatus.FORBIDDEN);
		}
	}*/

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
		//User user=new User();
		user.setPassword(bCryptPasswordEncoder.encode(reqUser.getPassword()));
		user.setUsername(reqUser.getUsername());
	//	user.setMail(reqUser.getMail());
		Role userRole = RoleDAO.findByRole("SUPERVIZOR");
		user.setRole(userRole);
		try {
			supervizorDAO.save(user);
		} catch (Exception e) {
			return new ResponseEntity<String>("User could not be saved!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("OK", HttpStatus.OK);

	}
	
	
	/*@Autowired
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
