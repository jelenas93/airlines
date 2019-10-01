package com.example.airlines.service;

import java.util.ArrayList;

import com.example.airlines.model.Airplane;

public interface AirplaneService {

	public ArrayList<Airplane> getAllByBrand(String brand);

	public Airplane getOneByBrandAndSeats(Airplane airplane);

	public Airplane getOneById(Long id);

	public ArrayList<Airplane> getAll();

	public String save(Airplane recObj);

	public String edit(Airplane recObj);

	public String notActive(Long id);
}
