package com.ita.sergey.petrosyuk.asynktasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {
	
	public String getJSON(String uri){
		HttpURLConnection connection = null;
		BufferedReader reader =  null;
		String jsonString = null;
		
		try {
			URL url = new URL(uri);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			jsonString = reader.readLine();
			jsonString = jsonString.substring(1, jsonString.length());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}// try

		return jsonString;

	}// getJSON
	
}// Connection
