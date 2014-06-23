package com.ita.sergey.petrosyuk.entity.resume;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.ita.sergey.petrosyuk.entity.ContainerData;

public class ContainerResume implements ContainerData<Resume> {
	@Expose
	private List<Resume> data = new ArrayList<Resume>();

	public List<Resume> getData() {
		return data;
	}

	public void setData(List<Resume> data) {
		this.data = data;
	}
}
