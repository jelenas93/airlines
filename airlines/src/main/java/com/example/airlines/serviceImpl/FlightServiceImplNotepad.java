package com.example.airlines.serviceImpl;

import java.util.ArrayList;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.airlines.model.Flight;
import com.example.airlines.service.FlightService;
@Service
@Profile("a")
public class FlightServiceImplNotepad implements FlightService {

	@Override
	public Flight getOne(Long id) {
		// TODO Auto-generated method stub
		return new Flight(null,1,null,null,null,null,true);
	}

	@Override
	public String notActive(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Flight> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(Flight recObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String edit(Flight recObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Flight> getAllActive() {
		// TODO Auto-generated method stub
		return null;
	}

}
