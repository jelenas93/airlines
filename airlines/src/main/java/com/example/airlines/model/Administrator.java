package com.example.airlines.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Administrator {

	@Id
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "airCompany", referencedColumnName = "id", nullable = false)
	private AirCompany airCompany;

	@Column(nullable = false)
	private boolean isActive;

	public Administrator() {

	}

	public Administrator(String username, String password, AirCompany airCompany, boolean isActive) {
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
