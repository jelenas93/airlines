package com.example.airlines.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Supervizor;

public interface SupervizorDAO extends CrudRepository<Supervizor, Long> {
	
	public Supervizor findOneByUsername(String name);
	public Supervizor findOneById(Long id);
	public Supervizor findOneByUsernameAndPassword(String name, String password);

}
