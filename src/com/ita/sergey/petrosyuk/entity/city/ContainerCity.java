package com.ita.sergey.petrosyuk.entity.city;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.ita.sergey.petrosyuk.entity.ContainerData;

public class ContainerCity implements ContainerData<City> {

	@Expose
	private List<City> data = new ArrayList<City>();

	public List<City> getData() {
		return data;
	}

	public void setData(List<City> data) {
		this.data = data;
	}

}