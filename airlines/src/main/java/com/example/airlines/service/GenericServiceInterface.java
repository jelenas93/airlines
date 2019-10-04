package com.example.airlines.service;

import java.util.ArrayList;

public interface GenericServiceInterface<T> {

	public ArrayList<T> getAll();

	public T getOne(String name);

	public String save(T recObj);

	public String edit(T recObj);

}
