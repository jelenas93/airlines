package com.example.airlines.serviceImpl;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
		// TODO Auto-generated method stub
		Iterable<Airplane> iter = airplaneDAO.findAll();
		return StreamSupport.stream(iter.spliterator(), false).filter(e -> e.isActive())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	public ArrayList<Airplane> getAllByBrand(String brand){
		return  (ArrayList<Airplane>)airplaneDAO.findAllByBrand(brand);
	}
	public Airplane getOneById(Long id) {
		// TODO Auto-generated method stub
		return (airplaneDAO.findById(id)).get();
	}
	public Airplane getOneByBrandAndSeats(Airplane airplane) {
		// TODO Auto-generated method stub
		return (airplaneDAO.findOneByBrandAndSeats(airplane.getBrand(),airplane.getSeats()));
	}

	@Override
	public String save(Airplane object) {
		// TODO Auto-generated method stub
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
	@Override
	public String notActive(Long id) {
		// TODO Auto-generated method stub
		Airplane airplane = (airplaneDAO.findById(id)).get();
		
		if (airplaneDAO.existsById(id)) {
			return "Greska, podaci nisu dobro uneseni.";
		}
		if (airplane == null) {
			return "Greska, avion ne postoji.";
		}
		airplane.setActive(false);

		try {
			airplaneDAO.save(airplane);
		} catch (IllegalArgumentException ex1) {
		//	log.error("[User Controller exception in DELETE: ]", ex1);
			return "Exception in Airplane Controller DELETE (ex1), contact admins!";
		} catch (Exception ex2) {
		//	log.error("[User Controller exception in DELETE: ]", ex2);
			return "Exception in Airplane Controller DELETE (ex2), contact admins!";
		}

		return "OK, uspjesno suspendovan!";
	}
}
