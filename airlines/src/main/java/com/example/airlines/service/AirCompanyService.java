package com.example.airlines.service;


import com.example.airlines.model.AirCompany;

public interface AirCompanyService extends GenericServiceInterface<AirCompany>{

	public String notActive(String name);
}
