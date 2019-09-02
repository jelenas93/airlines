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
}
