package com.example.airlines.service;

import com.example.airlines.model.Flight;

public interface FlightService extends GenericServiceInterface<Flight>{
	
	public String notActive(String name);

}
