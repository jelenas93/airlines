package com.example.airlines.service;

import java.util.ArrayList;

import com.example.airlines.model.Flight;

public interface FlightService {

	public Flight get(Long id);

	public String notActive(Long id);

	public ArrayList<Flight> getAll();

	public String save(Flight recObj);
	
	public String edit(Flight recObj) ;

}
