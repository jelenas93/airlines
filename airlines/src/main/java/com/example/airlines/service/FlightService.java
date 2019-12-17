package com.example.airlines.service;

import java.util.ArrayList;

//import org.springframework.stereotype.Service;

import com.example.airlines.model.Flight;
//@Service
public interface FlightService {

	public Flight getOne(Long id);

	public String notActive(Long id);

	public ArrayList<Flight> getAll();

	public String save(Flight recObj);
	
	public String edit(Flight recObj);
	
	public ArrayList<Flight> getAllActive();
	
	public ArrayList<Flight> getAllByAirCompany_Name(String name);
	
	public ArrayList<Flight> getAllByAirCompany_Id(Long id);

}
