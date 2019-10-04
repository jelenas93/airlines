package com.example.airlines.service;


import java.util.ArrayList;

import com.example.airlines.model.AirCompany;


public interface AirCompanyService {

	public String notActive(String name);
	public ArrayList<AirCompany> getAll();


	public AirCompany getOneByName(String name);
	
	public String save(AirCompany recObj);

	public String edit(AirCompany recObj);
}
