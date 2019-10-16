package com.example.airlines.serviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlines.dao.DestinationDAO;
import com.example.airlines.model.Destination;
import com.example.airlines.service.DestinationService;

@Service
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	DestinationDAO destinationDAO;

	@Override
	public ArrayList<Destination> getAll() {
		return (ArrayList<Destination>) destinationDAO.findAll();
	}

	@Override
	public Destination getOne(String name) {
		return destinationDAO.findOneByName(name);
	}

	@Override
	public String save(Destination object) {
		if (object.getName() == null || "".equals(object.getName())) {
			return "Greska, podaci nisu uneseni.";
		}
		Destination destination = destinationDAO.findOneByName(object.getName());
		if (destination != null) {
			return "Greska, destinacija sa datim imenom vec postoji.";
		}

		destination = new Destination(object.getName(), true);

		try {
			destinationDAO.save(destination);
		} catch (IllegalArgumentException ex1) {
			// log.error("[User Controller exception in POST: ]", ex1);
			return "Exception in Destination Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			// log.error("[User Controller exception in POST: ]", ex2);
			return "Exception in Destination Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String edit(Destination object) {
		if (object.getName() == null || "".equals(object.getName())) {
			return "Greska, podaci nisu uneseni.";
		}
		Optional<Destination> destinationPom = destinationDAO.findById(object.getId());
		if (destinationPom.isEmpty()) {
			return "Greska, destinacija ne postoji.";
		}
		Destination destination = destinationPom.get();
		if (destinationDAO.findOneByName(object.getName()) != null) {
			return "Greska, destinacija sa datim imenom vec postoji.";
		}
		destination.setName(object.getName());
		destination.setActive(object.getActive());
		try {
			destinationDAO.save(destination);
		} catch (IllegalArgumentException ex1) {
			// log.error("[Server Controller exception in PUT: ]", ex1);
			return "Exception in Destination Controller PUT (ex1), contact admins!";
		} catch (Exception ex2) {
			// log.error("[Server Controller exception in PUT: ]", ex2);
			return "Exception in Destination Controller PUT (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String notActive(String name) {
		Destination destination = destinationDAO.findOneByName(name);

		if (name == null || "".equals(name)) {
			return "Greska, podaci nisu uneseni.";
		}
		if (destination == null) {
			return "Greska, destinacija ne postoji.";
		}
		destination.setActive(false);

		try {
			destinationDAO.save(destination);
		} catch (IllegalArgumentException ex1) {
			// log.error("[User Controller exception in DELETE: ]", ex1);
			return "Exception in Destination Controller DELETE (ex1), contact admins!";
		} catch (Exception ex2) {
			// log.error("[User Controller exception in DELETE: ]", ex2);
			return "Exception in Destination Controller DELETE (ex2), contact admins!";
		}

		return "OK, uspjesno suspendovan!";
	}

	@Override
	public ArrayList<Destination> getAllActive() {
		return (ArrayList<Destination>) destinationDAO.findAllByIsActive(true);
	}
}
