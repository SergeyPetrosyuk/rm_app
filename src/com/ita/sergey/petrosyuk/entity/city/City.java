package com.ita.sergey.petrosyuk.entity.city;

import com.google.gson.annotations.Expose;
import com.ita.sergey.petrosyuk.interfaces.CityField;

public class City implements CityField {

	@Expose
	private String id;
	@Expose
	private String name;
	private int intId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setIntId(int id) {
		this.intId = id;
	}

	@Override
	public int getIntId() {
		return intId;
	}

}