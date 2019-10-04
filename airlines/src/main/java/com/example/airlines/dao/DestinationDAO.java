package com.example.airlines.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Destination;

public interface DestinationDAO extends CrudRepository<Destination, Long> {
	public Destination findOneById(Long id);
	public Destination findOneByName(String name);
}
