package com.ita.sergey.petrosyuk.jsonParser;

import com.google.gson.Gson;

public class ParserJSON<T> {
	private T object = null;
	
	public T parseJSONObject(String json, Class<T> returnClass){
		
		try {
			Gson gson = new Gson();
			object = gson.fromJson(json, returnClass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}// ParserJSON
