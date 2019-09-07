package com.example.airlines.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Supervizor;

public interface SupervizorDAO extends CrudRepository<Supervizor, Long> {
	
	public Supervizor findOneByName(String name);
	
	public Supervizor findOneByNameAndPassword(String name, String password);

}
