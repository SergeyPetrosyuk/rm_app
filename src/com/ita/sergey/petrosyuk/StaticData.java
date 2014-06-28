package com.ita.sergey.petrosyuk;

public class StaticData {
	public static final String EXTRA_TITLE 		  = "title";
	public static final String EXTRA_DESCRIPTION  = "description";
	public static final String LL_SALARY		  = "lowerLimitSalary";
	public static final String UL_SALARY		  = "UpperLimitSalary";
	public static final String EMPLOYER 		  = "employer";
	public static final String EXTRA_PUBLISH_DATE = "publish";
	public static final String EXTRA_LINK 		  = "link";
	public static final String EXTRA_SITE 		  = "site";
	public static final String EXTRA_CITY 		  = "city";
	public static final String FIELD			  = "field";
	
	public static final String URL = "url";
	
	public static final String ID   = "id";
	public static final String NAME = "name";
	
	public static final String GET_FIELDS 		  = "http://37.252.124.122/test/index.php/rest/getfields";
	public static final String GET_CITIES		  = "http://37.252.124.122/test/index.php/rest/getcities";
	public static final String GET_SEARCHED_DATA  = "http://37.252.124.122/test/index.php/rest/searchbuttonpressed";
	
	public static final String REGEX_WORK   = "[\\w\\W]*(work)[\\w\\W]*";
	public static final String REGEX_RABOTA = "[\\w\\W]*(rabota)[\\w\\W]*";
	public static final String REGEX_JOBS   = "[\\w\\W]*(jobs)[\\w\\W]*";
	public static final String REGEX_JOBIK  = "[\\w\\W]*(jobik)[\\w\\W]*";
	
	public static final int DEATIL_SEARCH_OPENED = 1;
	public static final int DEATIL_SEARCH_CLOSED = 0;
	public static Integer ARROW_STATUS 			 = 1;
	
	public static final int FIELDS    = 0;
	public static final int CITIES    = 1;
	public static final int RESUMES   = 2;
	public static final int VACANCIES = 3;
	
	public static final int SPINNER_RESUME  = 1;
	public static final int SPINNER_VACANCY = 0;
	
	public static final int REQUEST_CODE_FOR_CITY  = 0;
	public static final int REQUEST_CODE_FOR_FIELD = 1;
}
