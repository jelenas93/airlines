package com.example.airlines.dto;

public class DestinationDTO {

	public Long id;

	public String name;
	public DestinationDTO() {
		super();
	}

	public DestinationDTO(String name) {
		super();
		this.name = name;
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

}
