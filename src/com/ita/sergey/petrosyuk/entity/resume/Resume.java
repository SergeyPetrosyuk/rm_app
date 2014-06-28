package com.ita.sergey.petrosyuk.entity.resume;

import com.google.gson.annotations.Expose;
import com.ita.sergey.petrosyuk.interfaces.ResumeVacancy;

public class Resume implements ResumeVacancy {
	@Expose
	private String title;
	@Expose
	private String lower_limit_salary;
	@Expose
	private String upper_limit_salary;
	@Expose
	private String description;
	@Expose
	private String publishing;
	@Expose
	private String employer;
	@Expose
	private String city;
	@Expose
	private String link;
	@Expose
	private String site;
	@Expose
	private String field;
	
	private int id;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLower_limit_salary() {
		return lower_limit_salary;
	}

	public void setLower_limit_salary(String lower_limit_salary) {
		this.lower_limit_salary = lower_limit_salary;
	}

	public String getUpper_limit_salary() {
		return upper_limit_salary;
	}

	public void setUpper_limit_salary(String upper_limit_salary) {
		this.upper_limit_salary = upper_limit_salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishing() {
		return publishing;
	}

	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
