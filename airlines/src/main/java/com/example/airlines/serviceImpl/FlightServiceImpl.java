package com.example.airlines.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.airlines.dao.AirCompanyDAO;
import com.example.airlines.dao.AirplaneDAO;
import com.example.airlines.dao.DestinationDAO;
import com.example.airlines.dao.FlightDAO;
import com.example.airlines.model.AirCompany;
import com.example.airlines.model.Airplane;
import com.example.airlines.model.Destination;
import com.example.airlines.model.Flight;
import com.example.airlines.service.FlightService;

@Service
@Profile("default")
public class FlightServiceImpl implements FlightService {

	private FlightDAO flightDAO;

	private AirplaneDAO airplaneDAO;

	private DestinationDAO destinationDAO;

	private AirCompanyDAO airCompanyDAO;

	@Autowired
	public FlightServiceImpl(FlightDAO flightDAO, AirplaneDAO airplaneDAO, DestinationDAO destinationDAO,
			AirCompanyDAO airCompanyDAO) {
		this.flightDAO = flightDAO;
		this.airplaneDAO = airplaneDAO;
		this.destinationDAO = destinationDAO;
		this.airCompanyDAO = airCompanyDAO;
	}

	@Override
	public Flight getOne(Long id) {
		if (flightDAO.existsById(id))
			return flightDAO.findById(id).get();
		return null;
	}

	@Override
	public ArrayList<Flight> getAll() {
		return (ArrayList<Flight>) flightDAO.findAll();
	}

	@Override
	public String notActive(Long id) {
		if (flightDAO.existsById(id)) {
			Flight flight = (flightDAO.findById(id)).get();
			flight.setActive(false);
			try {
				flightDAO.save(flight);
			} catch (IllegalArgumentException ex1) {
				return "Exception in Flight Controller POST (ex1), contact admins!";
			} catch (Exception ex2) {
				return "Exception in Flight Controller POST (ex2), contact admins!";
			}
			return "OK, uspjesno ste suspendovali let.";

		}
		return "Greska, ne postoji dati let.";

	}

	@Override
	public String save(Flight recObj) {
		if (recObj.getAirplane() == null || recObj.getDestination() == null || recObj.getAirCompany() == null
				|| recObj.getFlightDate() == null || "".equals(recObj.getPrice() + "") || recObj.getPrice() <= 0) {
			return "Greska, niste unijeli sve podatke.";
		}

		Destination destination = destinationDAO.findOneByName(recObj.getDestination().getName());
		if (destination == null) {
			return "Greska, ne postoji unesena destinacija.";
		}
		Optional<Airplane> airplane = airplaneDAO.findById(recObj.getAirplane().getId());
		if (airplane.isEmpty()) {
			return "Greska, ne postoji uneseni avion.";
		}
		AirCompany airCompany = airCompanyDAO.findOneByName(recObj.getAirCompany().getName());
		if (airCompany == null) {
			return "Greska, ne postoji unesena avio kompanija.";
		}
		if(recObj.getFlightDate().before(new Date())) {
			return "Greska, datum je prosao.";
		}
		// kad kreiram let valjda mjesta nisu rezervisana, vec tek kad se karte kupuju
		// mjesta se popunjavaju
		Flight flight = new Flight(airplane.get(), 0, destination, airCompany, recObj.getFlightDate(),
				recObj.getPrice(), true);
		try {
			flightDAO.save(flight);
		} catch (IllegalArgumentException ex1) {
			return "Exception in Flight Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in Flight Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno ste unijeli let.";

	}

	@Override
	public String edit(Flight recObj) {
		if (recObj.getAirplane() == null || "".equals(recObj.getSeatReserved() + "") || recObj.getSeatReserved() < 0
				|| recObj.getDestination() == null || recObj.getAirCompany() == null || recObj.getFlightDate() == null
				|| "".equals(recObj.getPrice() + "") || recObj.getPrice() <= 0) {
			return "Greska, niste unijeli sve podatke.";
		}
		Optional<Airplane> airplane = airplaneDAO.findById(recObj.getAirplane().getId());
		if (airplane.isEmpty()) {
			return "Greska, ne postoji avion sa unesenim podacima.";
		}
		Destination destination = destinationDAO.findOneByName(recObj.getDestination().getName());
		if (destination == null) {
			return "Greska, ne postoji destinacija sa datim imenom.";
		}
		AirCompany airCompany = airCompanyDAO.findOneByName(recObj.getAirCompany().getName());
		if (airCompany == null) {
			return "Greska, ne postoji avio kompanija sa datim imenom.";
		}
		Optional<Flight> flight = flightDAO.findById(recObj.getId());
		if (flight.get() == null) {
			return "Greska, ne postoji dati let.";
		}

		int brojMogucihMjesta = airplane.get().getSeats();
		if (recObj.getSeatReserved() > brojMogucihMjesta) {
			return "Greska, ne mozete rezervisati više mjesta nego što ih ima u avionu.";
		}

		flight.get().setAirplane(airplane.get());
		flight.get().setSeatReserved(recObj.getSeatReserved());
		flight.get().setDestination(destination);
		flight.get().setAirCompany(airCompany);
		flight.get().setFlightDate(recObj.getFlightDate());
		flight.get().setPrice(recObj.getPrice());
		flight.get().setActive(recObj.getActive());
		try {
			flightDAO.save(flight.get());
		} catch (IllegalArgumentException ex1) {
			return "Exception in Flight Controller POST (ex1), contact admins!";
		} catch (Exception ex2) {
			return "Exception in Flight Controller POST (ex2), contact admins!";
		}
		return "OK, uspjesno ste izmjenili let.";
	}

	public ArrayList<Flight> getAllByAirCompany_Name(String name) {
		return flightDAO.findAllByAirCompany_Name(name);
	}

	public ArrayList<Flight> getAllByDestination_Name(String name) {
		return flightDAO.findAllByIsActiveAndDestination_Name(true, name);
	}

	public ArrayList<Flight> findAllByAirplane_Brand(String brand) {
		return flightDAO.findAllByAirplane_Brand(brand);
	}

	public ArrayList<Flight> getAllByFlightDate(Date date) {
		return flightDAO.findAllByFlightDate(date);
	}

	public ArrayList<Flight> findAllByPrice(Double price) {
		return flightDAO.findAllByPrice(price);
	}

	@Override
	public ArrayList<Flight> getAllActive() {
		return (ArrayList<Flight>) flightDAO.findAllByIsActive(true);
	}
	public ArrayList<Flight> getAllByAirCompany_Id(Long id) {
		return (ArrayList<Flight>) flightDAO.findAllByAirCompany_Id(id);
	}

}
