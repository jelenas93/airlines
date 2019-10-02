package com.example.airlines.service;


import com.example.airlines.model.Destination;

public interface DestinationService extends GenericServiceInterface<Destination> {
	public String notActive(String name);

}
