package com.example.airlines.service;

import java.util.ArrayList;

import com.example.airlines.model.Destination;

public interface DestinationService extends GenericServiceInterface<Destination> {

	public String notActive(String name);
	
	public ArrayList<Destination> getAllActive();

}
