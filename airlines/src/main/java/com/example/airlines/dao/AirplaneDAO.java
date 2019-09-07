package com.example.airlines.dao;

import com.example.airlines.model.Airplane;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface AirplaneDAO extends CrudRepository<Airplane, Long>{
	
	public ArrayList<Airplane> findAllByBrand(String brand);
	public ArrayList<Airplane> findAllBySeats(String seats);
	
}

