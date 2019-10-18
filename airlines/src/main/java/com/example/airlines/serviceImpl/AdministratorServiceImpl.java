package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlines.dao.AdministratorDAO;
import com.example.airlines.dao.AirCompanyDAO;
import com.example.airlines.model.Administrator;
import com.example.airlines.model.AirCompany;
import com.example.airlines.service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	AdministratorDAO adminDAO;
	
	@Autowired
	AirCompanyDAO airCompanyDAO;

	@Override
	public ArrayList<Administrator> getAll() {
		return (ArrayList<Administrator>) adminDAO.findAll();
	}

	@Override
	public Administrator getOne(String name) {
		return adminDAO.findOneByUsername(name);
	}

	@Override
	public Administrator getOneByUsernameAndPassword(String username, String password) {
		return adminDAO.findOneByUsernameAndPassword(username, password);
	}

	@Override
	public ArrayList<Administrator> getAllByAirCompany(AirCompany airCompany) {
		return (ArrayList<Administrator>) adminDAO.findAllByAirCompany(airCompany);
	}

	@Override
	public String save(Administrator object) {
		if (object.getUsername() == null || "".equals(object.getUsername()) || object.getPassword() == null
				|| "".equals(object.getPassword()) || object.getAirCompany() == null) {
			return "Greska, podaci nisu uneseni.";
		}
		AirCompany aircompany = airCompanyDAO.findOneByName(object.getAirCompany().getName());
		Administrator admin = adminDAO.findOneByUsername(object.getUsername());
		if (aircompany == null) {
			return "Greska, ne postoji unesena avio kompanuja.";
		}
		if (admin != null) {
			return "Greska, korisnik sa datim username-om vec postoji.";
		}

		admin = new Administrator(object.getUsername(), object.getPassword(), aircompany, true);

		try {
			adminDAO.save(admin);
		} catch (IllegalArgumentException ex1) {
			return "Exception in Administrator Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in Administrator Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String edit(Administrator object) {
		if (object.getUsername() == null || "".equals(object.getUsername()) || object.getPassword() == null
				|| "".equals(object.getPassword()) || object.getAirCompany() == null) {
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

		admin.setPassword(object.getPassword());
		admin.setAirCompany(airCompany);
		admin.setActive(object.getActive());
		try {
			adminDAO.save(admin);
		} catch (IllegalArgumentException ex1) {
			return "Exception in Server Controller PUT (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in Server Controller PUT (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";

	}

	@Override
	public String notActive(String username) {
		Administrator admin = adminDAO.findOneByUsername(username);
		if (username == null || "".equals(username)) {
			return "Greska, podaci nisu uneseni.";
		}
		if (admin == null) {
			return "Greska, korisnik ne postoji.";
		}
		admin.setActive(false);

		try {
			adminDAO.save(admin);
		} catch (IllegalArgumentException ex1) {
			return "Exception in User Controller DELETE (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in User Controller DELETE (ex2), contact admins!";
		}

		return "OK, uspjesno suspendovan!";
	}

	@Override
	public ArrayList<Administrator> getAllActive() {
		return (ArrayList<Administrator>) adminDAO.findAllByIsActive(true);
	}

}
