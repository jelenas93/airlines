package com.example.airlines.service;

import java.util.ArrayList;

import com.example.airlines.model.Administrator;
import com.example.airlines.model.AirCompany;

public interface AdministratorService extends GenericServiceInterface<Administrator> {

	public String notActive(String username);
	public Administrator getOneByUsernameAndPassword(String username,String password);
	public ArrayList<Administrator> getAllByAirCompany(AirCompany airCompany) ;
}
