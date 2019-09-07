package com.example.airlines.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Destination;

public interface DestinationDAO extends CrudRepository<Destination, Long> {

	public ArrayList<Destination> findAllByName(String name);
}
