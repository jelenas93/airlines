package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.airlines.dao.UserDAO;
import com.example.airlines.model.User;
import com.example.airlines.service.UserService;

public abstract class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public ArrayList<User> getAll() {
		return (ArrayList<User>) userDAO.findAll();
	}

	@Override
	public User getOne(String name) {
		return userDAO.findOneByName(name);
	}

	@Override
	public String save(User recObj) {
		if (recObj.getUsername() == null || "".equals(recObj.getUsername()) || recObj.getPassword() == null
				|| "".equals(recObj.getPassword()) || recObj.getMail() == null || "".equals(recObj.getMail()))
			return "Greska, podaci nisu uneseni.";
		User user = userDAO.findOneByName(recObj.getUsername());
		if (user != null)
			return "Greska, korisnik sa datim username-om vec postoji.";
		user = new User(recObj.getUsername(), recObj.getPassword(), recObj.getMail(), true);
		userDAO.save(user);
		return "OK, uspjesno sacuvano !";
	}

	@Override
	public String notActive(String name) {
		User user = userDAO.findOneByName(name);
		if (name == null || "".equals(name)) {
			return "Greska, niste unijeli username korisnika kog zelite suspendovati.";
		}
		if (user == null) {
			return "Greska, ne postoji korisnik sa datim username-om.";
		}
		user.setActive(false);

		userDAO.save(user);
		return "Ok, korisnik uspjesno suspendovan.";
	}

}
