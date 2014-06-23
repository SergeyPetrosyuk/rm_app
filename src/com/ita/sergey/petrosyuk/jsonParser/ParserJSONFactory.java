package com.ita.sergey.petrosyuk.jsonParser;

import java.util.List;

import com.ita.sergey.petrosyuk.StaticData;
import com.ita.sergey.petrosyuk.entity.ContainerData;
import com.ita.sergey.petrosyuk.entity.city.City;
import com.ita.sergey.petrosyuk.entity.city.ContainerCity;
import com.ita.sergey.petrosyuk.entity.field.ContainerField;
import com.ita.sergey.petrosyuk.entity.field.Field;
import com.ita.sergey.petrosyuk.entity.resume.ContainerResume;
import com.ita.sergey.petrosyuk.entity.resume.Resume;
import com.ita.sergey.petrosyuk.entity.vacancy.ContainerVacancy;
import com.ita.sergey.petrosyuk.entity.vacancy.Vacancy;

public class ParserJSONFactory {
	
	public List getData(int type, String json){
		List data = null;
		
		switch (type) {
			case StaticData.FIELDS:
				ParserJSON<ContainerField> parserField = new ParserJSON<ContainerField>();
				ContainerData<Field> containerField = parserField.parseJSONObject(json, ContainerField.class);
				data = containerField.getData();
				break;
				
			case StaticData.CITIES:
				ParserJSON<ContainerCity> parserCity = new ParserJSON<ContainerCity>();
				ContainerData<City> containerCity = parserCity.parseJSONObject(json, ContainerCity.class);
				data = containerCity.getData();
				break;
				
			case StaticData.RESUMES:
				ParserJSON<ContainerResume> parserResume = new ParserJSON<ContainerResume>();
				ContainerData<Resume> containerResume = parserResume.parseJSONObject(json, ContainerResume.class);
				data = containerResume.getData();
				break;
			
			case StaticData.VACANCIES:
				ParserJSON<ContainerVacancy> parserVacancy = new ParserJSON<ContainerVacancy>();
				ContainerData<Vacancy> containerVacancy = parserVacancy.parseJSONObject(json, ContainerVacancy.class);
				data = containerVacancy.getData();
				break;
				
			default:
				break;
		}// switch
		
		return data;
	}// getData
	
}// ParserJSONFactory
