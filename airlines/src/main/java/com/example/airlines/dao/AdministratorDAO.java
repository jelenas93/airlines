package com.example.airlines.dao;


import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Administrator;
import com.example.airlines.model.AirCompany;

public interface AdministratorDAO extends CrudRepository<Administrator, Long> {

	public ArrayList<Administrator> findAll();
	public Administrator findOneByUsername(String username);
	public Administrator findOneByUsernameAndPassword(String name, String password);
	public ArrayList<Administrator> findAllByAirCompany(AirCompany airCompany);
	public Administrator findOneById(Long id);

}
