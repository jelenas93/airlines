package com.example.airlines.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.User;

public interface UserDAO extends CrudRepository<User, Long>{
	
	public User findOneByName(String name);
	
	public User findOneByNameAndPassword(String name, String password);

}
