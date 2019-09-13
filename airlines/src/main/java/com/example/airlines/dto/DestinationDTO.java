package com.example.airlines.dto;

public class DestinationDTO {

	private Long id;

	private String name;
	
	private boolean isActive;
	
	public DestinationDTO() {
		super();
	}

	public DestinationDTO(String name, boolean isAvtive) {
		super();
		this.name = name;
		this.isActive=isActive;
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
