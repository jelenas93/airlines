package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlines.dao.DestinationDAO;
import com.example.airlines.model.Administrator;
import com.example.airlines.model.AirCompany;
import com.example.airlines.model.Destination;
import com.example.airlines.service.DestinationService;
@Service
public class DestinationServiceImpl  implements DestinationService{
	@Autowired
	DestinationDAO destinationDAO;
	@Override
	public ArrayList<Destination> getAll() {
		// TODO Auto-generated method stub
		return (ArrayList<Destination>) destinationDAO.findAll();
	}

	@Override
	public Destination getOne(String name) {
		// TODO Auto-generated method stub
		return destinationDAO.findOneByName(name);
	}

	@Override
	
	public String save(Destination object) {
		// TODO Auto-generated method stub
		if (object.getName() == null || object.getName().equals("") ) {
			return "Greska, podaci nisu uneseni.";
		}
		Destination destination = destinationDAO.findOneByName(object.getName());
		if (destination != null) {
			return "Greska, destinacija sa datim imenom vec postoji.";
		}

		destination = new Destination(object.getName(), object.isActive());

		try {
			destinationDAO.save(destination);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[User Controller exception in POST: ]", ex1);
			return "Exception in Destination Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[User Controller exception in POST: ]", ex2);
			return "Exception in Destination Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String edit(Destination object) {
		// TODO Auto-generated method stub
		if (object.getName() == null || object.getName().equals("") ) {
			return "Greska, podaci nisu uneseni.";
		}
		Destination destination = destinationDAO.findOneByName(object.getName());
		if (destination == null) {
			return "Greska, destinacija sa datim imenom ne postoji.";
		}
		destination.setName(object.getName());
		try {
			destinationDAO.save(destination);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[Server Controller exception in PUT: ]", ex1);
			return "Exception in Destination Controller PUT (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[Server Controller exception in PUT: ]", ex2);
			return "Exception in Destination Controller PUT (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}
	@Override
	public String notActive(String name) {
		// TODO Auto-generated method stub
		Destination destination = destinationDAO.findOneByName(name);
		if (name == null || name.equals("")) {
			return "Greska, podaci nisu uneseni.";
		}
		if (destinationDAO == null) {
			return "Greska, destinacija ne postoji.";
		}
		destination.setActive(false);

		try {
			destinationDAO.save(destination);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[User Controller exception in DELETE: ]", ex1);
			return "Exception in Destination Controller DELETE (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[User Controller exception in DELETE: ]", ex2);
			return "Exception in Destination Controller DELETE (ex2), contact admins!";
		}

		return "OK, uspjesno suspendovan!";
	}
}
