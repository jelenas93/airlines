package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlines.dao.AdministratorDAO;
import com.example.airlines.dao.SupervizorDAO;
import com.example.airlines.dao.UserDAO;
import com.example.airlines.model.Administrator;
import com.example.airlines.model.Supervizor;
import com.example.airlines.model.User;
import com.example.airlines.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	SupervizorDAO supervizorDAO;
	
	@Autowired
	AdministratorDAO adminDAO;

	@Override
	public ArrayList<User> getAll() {
		return (ArrayList<User>) userDAO.findAll();
	}

	@Override
	public User getOne(String name) {
		return userDAO.findOneByUsername(name);
	}

	@Override
	public String save(User recObj) {
		if (recObj.getUsername() == null || "".equals(recObj.getUsername()) || recObj.getPassword() == null
				|| "".equals(recObj.getPassword()) || recObj.getMail() == null || "".equals(recObj.getMail()))
			return "Greska, podaci nisu uneseni.";
		User user = userDAO.findOneByUsername(recObj.getUsername());
		Administrator admin=adminDAO.findOneByUsername(recObj.getUsername());
		Supervizor supervizor=supervizorDAO.findOneByUsername(recObj.getUsername());
		if (user != null || admin !=null || supervizor != null)
			return "Greska, korisnik sa datim username-om vec postoji.";
		user = new User(recObj.getUsername(), recObj.getPassword(), recObj.getMail(), true);
		try {
			userDAO.save(user);
		} catch (IllegalArgumentException ex1) {
			return "Exception in User Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in User Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano !";
	}

	@Override
	public String notActive(String name) {
		if (name == null || "".equals(name)) {
			return "Greska, niste unijeli username korisnika kog zelite suspendovati.";
		}
		User user = userDAO.findOneByUsername(name);
		if (user == null) {
			return "Greska, ne postoji korisnik sa datim username-om.";
		}
		user.setActive(false);
		try {
			userDAO.save(user);
		} catch (IllegalArgumentException ex1) {
			return "Exception in User Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in User Controller POST (ex2), contact admins!";
		}
		return "Ok, korisnik uspjesno suspendovan.";
	}

	@Override
	public String edit(User recObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUsernameAndPassword(String name, String password) {
		return userDAO.findOneByUsernameAndPassword(name, password);
	}

	@Override
	public ArrayList<User> getAllActive() {
		return (ArrayList<User>) userDAO.findAllByIsActive(true);
	}

	

}
