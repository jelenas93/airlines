package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlines.dao.AirplaneDAO;
import com.example.airlines.model.Airplane;
import com.example.airlines.service.AirplaneService;

@Service
public class AirplaneServiceImpl implements AirplaneService{
	
	@Autowired
	AirplaneDAO airplaneDAO;
	
	@Override
	public ArrayList<Airplane> getAll() {
		return (ArrayList<Airplane>) airplaneDAO.findAll();
		
	}
	public ArrayList<Airplane> getAllByBrand(String brand){
		return  (ArrayList<Airplane>)airplaneDAO.findAllByBrand(brand);
	}
	public Airplane getOneById(Long id) {
		return (airplaneDAO.findById(id)).get();
	}
	public Airplane getOneByBrandAndSeats(Airplane airplane) {
		return (airplaneDAO.findOneByBrandAndSeats(airplane.getBrand(),airplane.getSeats()));
	}

	@Override
	public String save(Airplane object) {
		if ( object.getSeats()<0 || object.getBrand() == null
				|| "".equals(object.getBrand()) ) {
			return "Greska, podaci nisu uneseni.";
		}
	
		Airplane airplane = new Airplane( object.getSeats(), object.getBrand(), true);
		if(this.getOneByBrandAndSeats(airplane)!=null) {
			return "Greska, avion sa tim podacima vec postoji.";
		}
		try {
			airplaneDAO.save(airplane);
		} catch (IllegalArgumentException ex1) {
			return "Exception in Airplane Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in Airplane Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}

	@Override
	public String edit(Airplane object) {
		if (object.getBrand() == null || "".equals(object.getBrand()) 
				|| object.getSeats()<0) {
			return "Greska, podaci nisu uneseni.";
		}
		Airplane airplane = airplaneDAO.findOneById(object.getId());
	    if (airplane == null) {
			return "Greska, avion sa datim podacima ne postoji.";
		}
		
	    airplane.setBrand(object.getBrand());
	    airplane.setSeats(object.getSeats());
	    airplane.setActive(object.getActive());
		try {
			airplaneDAO.save(airplane);
		} catch (IllegalArgumentException ex1) {
			return "Exception in Airplane Controller PUT (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in Airplane Controller PUT (ex2), contact admins!";
		}
		return "OK, uspjesno sacuvano!";
	}
	@Override
	public String notActive(Long id) {
		Airplane airplane = (airplaneDAO.findById(id)).get();
		
		if (!airplaneDAO.existsById(id)) {
			return "Greska, podaci nisu dobro uneseni.";
		}
		if (airplane == null) {
			return "Greska, avion ne postoji.";
		}
		airplane.setActive(false);

		try {
			airplaneDAO.save(airplane);
		} catch (IllegalArgumentException ex1) {
			return "Exception in Airplane Controller DELETE (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in Airplane Controller DELETE (ex2), contact admins!";
		}

		return "OK, uspjesno suspendovan!";
	}
	@Override
	public ArrayList<Airplane> getAllActive() {
		return (ArrayList<Airplane>) airplaneDAO.findAllByIsActive(true);
	}
}
