package com.example.airlines.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.airlines.model.Role;

public interface RoleDAO extends CrudRepository<Role, Long> {

	public Role findByRole(String role);

}
