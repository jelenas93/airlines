package com.example.airlines.service;

import com.example.airlines.model.Administrator;

public interface AdministratorService extends GenericServiceInterface<Administrator> {

	public String notActive(String username);
}
