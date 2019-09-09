package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.airlines.dao.AirplaneDAO;
import com.example.airlines.model.Administrator;
import com.example.airlines.model.AirCompany;
import com.example.airlines.model.Airplane;
import com.example.airlines.service.AirplaneService;

public abstract class AirplaneServiceImpl implements AirplaneService{
	@Autowired
	AirplaneDAO airplaneDAO;
	@Override
	public ArrayList<Airplane> getAll() {
		// TODO Auto-generated method stub
		return (ArrayList<Airplane>) airplaneDAO.findAll();
	}
	public ArrayList<Airplane> getAllByBrand(String brand){
		return  (ArrayList<Airplane>)airplaneDAO.findAllByBrand(brand);
	}

	public Airplane getOneById(Long id) {
		// TODO Auto-generated method stub
		return (airplaneDAO.findById(id)).get();
	}

	@Override
	public String save(Airplane object) {
		// TODO Auto-generated method stub
		if ( object.getSeats()>0 || object.getBrand() == null
				|| object.getBrand().equals("") ) {
			return "Greska, podaci nisu uneseni.";
		}
	

		Airplane airplane = new Airplane( object.getSeats(), object.getBrand());

		try {
			airplaneDAO.save(airplane);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[User Controller exception in POST: ]", ex1);
			return "Exception in Airplane Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[User Controller exception in POST: ]", ex2);
			return "Exception in Airplane Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String edit(Airplane object) {
		// TODO Auto-generated method stub
		if (object.getBrand() == null || object.getBrand().equals("") 
				|| object.getSeats()>0) {
			return "Greska, podaci nisu uneseni.";
		}
		Airplane airplane = (airplaneDAO.findById(object.getId())).get();
	    if (airplane == null) {
			return "Greska, avion sa datim podacima ne postoji.";
		}
		
	    airplane.setBrand(object.getBrand());
	    airplane.setSeats(object.getSeats());
		try {
			airplaneDAO.save(airplane);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[Server Controller exception in PUT: ]", ex1);
			return "Exception in Airplane Controller PUT (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[Server Controller exception in PUT: ]", ex2);
			return "Exception in Airplane Controller PUT (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

}
