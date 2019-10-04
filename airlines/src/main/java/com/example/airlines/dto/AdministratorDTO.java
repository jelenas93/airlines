package com.example.airlines.dto;

import com.example.airlines.model.AirCompany;

public class AdministratorDTO {

	private Long id;

	private String username;

	private String password;

	private AirCompany airCompany;

	private boolean isActive;

	public AdministratorDTO() {

	}

	public AdministratorDTO(String username, String password, AirCompany airCompany, boolean isActive) {
		super();
		this.username = username;
		this.password = password;
		this.airCompany = airCompany;
		this.isActive = isActive;
	}

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
