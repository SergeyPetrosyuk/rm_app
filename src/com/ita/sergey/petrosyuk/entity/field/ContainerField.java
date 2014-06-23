package com.ita.sergey.petrosyuk.entity.field;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.ita.sergey.petrosyuk.entity.ContainerData;

public class ContainerField implements ContainerData<Field> {
	@Expose
	private List<Field> data = new ArrayList<Field>();

	public List<Field> getData() {
		return data;
	}

	public void setData(List<Field> data) {
		this.data = data;
	}
}
