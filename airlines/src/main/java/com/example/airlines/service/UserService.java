package com.example.airlines.service;

import java.util.ArrayList;

import com.example.airlines.model.User;

public interface UserService extends GenericServiceInterface<User>{
	
	public ArrayList<User> getAllActive();
	
	public String notActive(String name);
	
	public User getByUsernameAndPassword(String name, String password);

}
