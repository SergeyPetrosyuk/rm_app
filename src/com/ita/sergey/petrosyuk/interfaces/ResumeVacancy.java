package com.ita.sergey.petrosyuk.interfaces;

import java.util.List;

public interface ResumeVacancy {
	public String getTitle();

	public void setTitle(String title);

	public String getLower_limit_salary();

	public void setLower_limit_salary(String lower_limit_salary);

	public String getUpper_limit_salary();

	public void setUpper_limit_salary(String upper_limit_salary);

	public String getDescription();

	public void setDescription(String description);

	public String getPublishing();

	public void setPublishing(String publishing);

	public String getEmployer();

	public void setEmployer(String employer);

	public String getCity();

	public void setCity(String city);

	public String getLink();

	public void setLink(String link);

	public String getSite();

	public void setSite(String site);

	public String getField();

	public void setField(String field);
	
	public void setId(int id);
	
	public int getId();
}
