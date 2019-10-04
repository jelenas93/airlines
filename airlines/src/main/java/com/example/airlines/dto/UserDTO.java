package com.example.airlines.dto;

public class UserDTO {

	private Long id;

	private String username;

	private String password;

	private String mail;

	private boolean isActive;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(String username, String password, String mail, boolean isActive) {
		super();
		this.username = username;
		this.password = password;
		this.mail = mail;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
