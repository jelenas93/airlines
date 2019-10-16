package com.example.airlines.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Destination;

public interface DestinationDAO extends CrudRepository<Destination, Long> {
	
	public Destination findOneById(Long id);
	
	public Destination findOneByName(String name);
	
	public ArrayList<Destination> findAllByIsActive(Boolean bool);
}
