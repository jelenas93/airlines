package com.example.airlines.dao;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Administrator;


public interface AdministratorDAO extends CrudRepository<Administrator, Long> {

	

}
