package com.example.airlines.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

import com.example.airlines.model.AirCompany;

public interface AirCompanyDAO extends CrudRepository<AirCompany, Long> {
	public ArrayList<AirCompany> findAll();

	public AirCompany findOneByUsername(String username);

	public AirCompany findOneByNameAndPassword(String name, String password);

}
