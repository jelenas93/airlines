package com.example.airlines.serviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlines.dao.AirCompanyDAO;
import com.example.airlines.model.AirCompany;
import com.example.airlines.service.AirCompanyService;

@Service
public class AirCompanyServiceImpl implements AirCompanyService {

	private AirCompanyDAO airCompanyDAO;
	
	@Autowired
	public AirCompanyServiceImpl(AirCompanyDAO airCompanyDAO) {
		this.airCompanyDAO = airCompanyDAO;
	}

	@Override
	public ArrayList<AirCompany> getAll() {
		return (ArrayList<AirCompany>) airCompanyDAO.findAll();
	}

	public AirCompany getOne(String name) {
		return airCompanyDAO.findOneByName(name);
	}

	@Override
	public String save(AirCompany object) {
		if (object.getName() == null || "".equals(object.getName())) {
			return "Greska, podaci nisu uneseni.";
		}
		AirCompany airCompany = airCompanyDAO.findOneByName(object.getName());
		if (airCompany != null) {
			return "Greska, avio kompanija vec postoji.";
		}

		airCompany = new AirCompany(object.getName(), true);

		try {
			airCompanyDAO.save(airCompany);
		} catch (IllegalArgumentException ex1) {
			return "Exception in AirCompany Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in AirCompany Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String edit(AirCompany object) {
		if (object.getName() == null || "".equals(object.getName())) {
			return "Greska, podaci nisu uneseni.";
		}

		Optional<AirCompany> airCompanyPom = airCompanyDAO.findById(object.getId());
		if (airCompanyPom.isEmpty()) {
			return "Greska, avio kompanija ne postoji.";
		}
		if (airCompanyDAO.findOneByName(object.getName()) != null) {
			return "Greska, avio kompanija sa datim imenom vec postoji.";
		}
		AirCompany airCompany = airCompanyPom.get();
		airCompany.setName(object.getName());
		airCompany.setActive(object.getActive());
		try {
			airCompanyDAO.save(airCompany);
		} catch (IllegalArgumentException ex1) {
			return "Exception in AirCompany Controller PUT (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in AirCompany Controller PUT (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String notActive(String name) {
		AirCompany airCompany = airCompanyDAO.findOneByName(name);
		if (name == null || "".equals(name)) {
			return "Greska, podaci nisu uneseni.";
		}
		if (airCompany == null) {
			return "Greska, avio kompanija ne postoji.";
		}
		airCompany.setActive(false);

		try {
			airCompanyDAO.save(airCompany);
		} catch (IllegalArgumentException ex1) {
			return "Exception in AirCompany Controller DELETE (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in AirCompany Controller DELETE (ex2), contact admins!";
		}

		return "OK, uspjesno suspendovan!";
	}

	@Override
	public ArrayList<AirCompany> getAllActive() {
		return (ArrayList<AirCompany>) airCompanyDAO.findAllByIsActive(true);
	}

}
