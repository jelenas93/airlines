package com.example.airlines.dao;


import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Administrator;


public interface AdministratorDAO extends CrudRepository<Administrator, Long> {

	public ArrayList<Administrator> findAll();
	public Administrator findOneByUsername(String username);
	public Administrator findOneByNameAndPassword(String name, String password);
	public ArrayList<Administrator> findAllByAirCompany_Name(String name);
	

}
