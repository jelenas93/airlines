package com.example.airlines.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Flight;

public interface FlightDAO extends CrudRepository<Flight, Long> {
	
	public ArrayList<Flight> findAllByAirCompany_Name(String name);
	
	public ArrayList<Flight> findAllByDestination_Name(String name);
	
	public ArrayList<Flight> findAllByAirplane_Brand(String brand);
	
	public ArrayList<Flight> findAllByFlightDate(Date date); 
	
	public ArrayList<Flight> findAllByPrice(Double price);

	public Flight findOneById(Long id);
	
	public ArrayList<Flight> findAllByIsActive(Boolean bool);
	
	public ArrayList<Flight> findAllByAirCompany_Id(Long id);
}
