package com.example.airlines.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Administrator {

	@Id
	public Long id;
	
	@Column(nullable = false)
	public String username;
	
	@Column(nullable = false)
	public String passwprd;
	
	@ManyToOne
	@JoinColumn(name="airCompany", referencedColumnName="id", nullable=false)
	public AirCompany airCompany;
	
	@Column(nullable = false)
	public boolean isActive;

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

	public String getPasswprd() {
		return passwprd;
	}

	public void setPasswprd(String passwprd) {
		this.passwprd = passwprd;
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
