package com.example.airlines.service;

import java.util.ArrayList;

import com.example.airlines.model.User;

public interface UserService {

	public String notActive(String name);

	public ArrayList<User> getAll();

	public User getOne(String name);

	public String save(User recObj);

}
