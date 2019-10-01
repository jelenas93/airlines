package com.example.airlines.serviceImpl;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
		// TODO Auto-generated method stub
		Iterable<Administrator> iter = adminDAO.findAll();
		return StreamSupport.stream(iter.spliterator(), false).filter(e -> e.isActive())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public Administrator getOne(String name) {
		// TODO Auto-generated method stub
		return adminDAO.findOneByUsername(name);
	}

	@Override
	public Administrator getOneByUsernameAndPassword(String username, String password) {
		return adminDAO.findOneByUsernameAndPassword(username, password);
	}
	
	@Override
	public ArrayList<Administrator> getAllByAirCompany(AirCompany airCompany) {
		return (ArrayList<Administrator>)adminDAO.findAllByAirCompany(airCompany);
	}
	@Override
	public String save(Administrator object) {
		// TODO Auto-generated method stub
		if (object.getUsername() == null || "".equals(object.getUsername()) || object.getPassword() == null
				|| "".equals(object.getPassword()) || object.getAirCompany() == null) {
			return "Greska, podaci nisu uneseni.";
		}
		AirCompany aircompany=airCompanyDAO.findOneByName(object.getAirCompany().getName());
		Administrator admin = adminDAO.findOneByUsername(object.getUsername());
		if(aircompany==null) {
			return "Greska, ne postoji unesena avio kompanuja.";
		}
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
		if (object.getUsername() == null || "".equals(object.getUsername()) || object.getPassword() == null
				|| "".equals(object.getPassword()) || object.getAirCompany() == null) {
			return "Greska, podaci nisu uneseni.";
		}
		/*
		AirCompany airCompany = airCompanyDAO.findOneByName(object.getAirCompany().getName());
		if (airCompany == null) {
			return "Greska, avio kompanija sa datim imenom ne postoji.";
		}
*/
		Administrator admin = adminDAO.findOneByUsername(object.getUsername());
		if (admin == null) {
			return "Greska, korisnik sa datim username-om ne postoji.";
		}
		
		admin.setPassword(object.getPassword());
		admin.setActive(object.isActive());
		//admin.setAirCompany(airCompany);
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
		//	log.error("[User Controller exception in DELETE: ]", ex1);
			return "Exception in User Controller DELETE (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[User Controller exception in DELETE: ]", ex2);
			return "Exception in User Controller DELETE (ex2), contact admins!";
		}

		return "OK, uspjesno suspendovan!";
	}

}
