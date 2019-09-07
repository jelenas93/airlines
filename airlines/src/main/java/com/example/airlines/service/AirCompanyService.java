package com.example.airlines.service;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.AirCompany;

public interface AirCompanyService extends CrudRepository<AirCompany, Long>{

	public String notActive(String name);
}
