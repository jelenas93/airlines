package com.example.airlines.dto;

public class AirCompanyDTO {

	public Long id;

	public String name;

	public boolean isActive;
	public AirCompanyDTO() {
		super();
	}

	public AirCompanyDTO(String name, boolean isActive) {
		super();
		this.name = name;
		this.isActive = isActive;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
