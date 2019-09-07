package com.example.airlines.service;

import com.example.airlines.model.User;

public interface UserService extends GenericServiceInterface<User>{
	
	public String notActive(String name);

}
