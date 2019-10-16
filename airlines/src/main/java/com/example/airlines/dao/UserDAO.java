package com.example.airlines.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.User;

public interface UserDAO extends CrudRepository<User, Long>{
	
	public User findOneByUsername(String name);
	
	public User findOneByUsernameAndPassword(String name, String password);
	
	public ArrayList<User> findAllByIsActive(Boolean bool);

}
