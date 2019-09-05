package com.example.airlines.dto;

import com.example.airlines.model.AirCompany;

public class AdministratorDTO {
	public String password;
	public AirCompany airCompany;
	public boolean isActive;
	public Long id;
	public String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AirCompany getAirCompany() {
		return airCompany;
	}

	public void setAirCompany(AirCompany airCompany) {
		this.airCompany = airCompany;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
