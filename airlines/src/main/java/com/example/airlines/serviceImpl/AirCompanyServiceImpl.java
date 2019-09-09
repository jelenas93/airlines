package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.airlines.dao.AirCompanyDAO;
import com.example.airlines.model.Administrator;
import com.example.airlines.model.AirCompany;
import com.example.airlines.model.Destination;
import com.example.airlines.service.AirCompanyService;

public abstract class AirCompanyServiceImpl  implements AirCompanyService{
	@Autowired
	AirCompanyDAO airCompanyDAO;
	@Override
	public ArrayList<AirCompany> getAll() {
		// TODO Auto-generated method stub
		return (ArrayList<AirCompany>) airCompanyDAO.findAll();
	}


	public AirCompany getOneById(Long id) {
		// TODO Auto-generated method stub
		return (airCompanyDAO.findById(id)).get();
	}

	@Override
	public String save(AirCompany object) {
		// TODO Auto-generated method stub
		if (object.getName() == null || object.getName().equals("") ) {
			return "Greska, podaci nisu uneseni.";
		}
		AirCompany airCompany = airCompanyDAO.findOneByName(object.getName());
		if (airCompany != null) {
			return "Greska, korisnik sa datim username-om vec postoji.";
		}

		airCompany = new AirCompany(object.getName(), true);

		try {
			airCompanyDAO.save(airCompany);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[User Controller exception in POST: ]", ex1);
			return "Exception in AirCompany Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[User Controller exception in POST: ]", ex2);
			return "Exception in AirCompany Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String edit(AirCompany object) {
		// TODO Auto-generated method stub
		if (object.getName() == null || object.getName().equals("") ) {
			return "Greska, podaci nisu uneseni.";
		}
		AirCompany airCompany = airCompanyDAO.findOneByName(object.getName());
		if (airCompany == null) {
			return "Greska, airCompany sa datim imenom ne postoji.";
		}
		airCompany.setName(object.getName());
		airCompany.setActive(true);
		try {
			airCompanyDAO.save(airCompany);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[Server Controller exception in PUT: ]", ex1);
			return "Exception in AirCompany Controller PUT (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[Server Controller exception in PUT: ]", ex2);
			return "Exception in AirCompany Controller PUT (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String notActive(String name) {
		// TODO Auto-generated method stub
		AirCompany airCompany = airCompanyDAO.findOneByName(name);
		if (name == null || name.equals("")) {
			return "Greska, podaci nisu uneseni.";
		}
		if (airCompany == null) {
			return "Greska, avio kompanija ne postoji.";
		}
		airCompany.setActive(false);

		try {
			airCompanyDAO.save(airCompany);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[User Controller exception in DELETE: ]", ex1);
			return "Exception in AirCompany Controller DELETE (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[User Controller exception in DELETE: ]", ex2);
			return "Exception in AirCompany Controller DELETE (ex2), contact admins!";
		}

		return "OK, uspjesno suspendovan!";
	}

}
