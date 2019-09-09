package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.airlines.dao.AdministratorDAO;
import com.example.airlines.dao.AirCompanyDAO;
import com.example.airlines.model.Administrator;
import com.example.airlines.model.AirCompany;
import com.example.airlines.service.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
	
	@Autowired
	AdministratorDAO adminDAO;
	@Autowired
	AirCompanyDAO airCompanyDAO;
	@Override
	public ArrayList<Administrator> getAll() {
		// TODO Auto-generated method stub
		return (ArrayList<Administrator>) adminDAO.findAll();
	}

	@Override
	public Administrator getOne(String name) {
		// TODO Auto-generated method stub
		return adminDAO.findOneByUsername(name);
	}

	@Override
	public String save(Administrator object) {
		// TODO Auto-generated method stub
		if (object.getUsername() == null || object.getUsername().equals("") || object.getPassword() == null
				|| object.getPassword().equals("") || object.getAirCompany() == null
						|| object.getAirCompany().equals("")) {
			return "Greska, podaci nisu uneseni.";
		}
		Administrator admin = adminDAO.findOneByUsername(object.getUsername());
		if (admin != null) {
			return "Greska, korisnik sa datim username-om vec postoji.";
		}

		admin = new Administrator(object.getUsername(), object.getPassword(), object.getAirCompany(), true);

		try {
			adminDAO.save(admin);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[User Controller exception in POST: ]", ex1);
			return "Exception in Administrator Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[User Controller exception in POST: ]", ex2);
			return "Exception in Administrator Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String edit(Administrator object) {
		// TODO Auto-generated method stub
		if (object.getUsername() == null || object.getUsername().equals("") || object.getPassword() == null
				|| object.getPassword().equals("") || object.getAirCompany() == null) {
			return "Greska, podaci nisu uneseni.";
		}
		AirCompany airCompany = airCompanyDAO.findOneByName(object.getAirCompany().getName());
		if (airCompany == null) {
			return "Greska, avio kompanija sa datim imenom ne postoji.";
		}
		Administrator admin = adminDAO.findOneByUsername(object.getUsername());
		if (admin == null) {
			return "Greska, korisnik sa datim username-om ne postoji.";
		}
		admin.setUsername(object.getUsername());
		admin.setPassword(object.getPassword());
		admin.setAirCompany(airCompany);
		try {
			adminDAO.save(admin);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[Server Controller exception in PUT: ]", ex1);
			return "Exception in Server Controller PUT (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[Server Controller exception in PUT: ]", ex2);
			return "Exception in Server Controller PUT (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
		
	}

	@Override
	public String notActive(String username) {
		// TODO Auto-generated method stub
		Administrator admin = adminDAO.findOneByUsername(username);
		if (username == null || username.equals("")) {
			return "Greska, podaci nisu uneseni.";
		}
		if (admin == null) {
			return "Greska, korisnik ne postoji.";
		}
		admin.setActive(false);

		try {
			adminDAO.save(admin);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[User Controller exception in DELETE: ]", ex1);
			return "Exception in User Controller DELETE (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[User Controller exception in DELETE: ]", ex2);
			return "Exception in User Controller DELETE (ex2), contact admins!";
		}

		return "OK, uspjesno suspendovan!";
	}

}
