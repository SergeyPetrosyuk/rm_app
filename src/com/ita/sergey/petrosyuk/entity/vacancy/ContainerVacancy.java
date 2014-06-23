package com.ita.sergey.petrosyuk.entity.vacancy;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.ita.sergey.petrosyuk.entity.ContainerData;

public class ContainerVacancy implements ContainerData<Vacancy> {
	@Expose
	private List<Vacancy> data = new ArrayList<Vacancy>();

	public List<Vacancy> getData() {
		return data;
	}

	public void setData(List<Vacancy> data) {
		this.data = data;
	}
}
