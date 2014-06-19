package com.ita.robotamolodi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RestTask extends AsyncTask<String, Void, String> {
	
	public final String LOG_TAG = "myLogs";

	private final List<Resume> resumes;
	private URL url = null;
	private HttpURLConnection connection = null;
	private String jsonData = null;
	
	private TextView loadingStatus;
	private ProgressBar bar;
	private ImageView logo;
	private String searchRequest;
	
	public RestTask(List<Resume> resumes, TextView loadingStatus, ProgressBar bar, ImageView logo, String searchRequest) {
		this.logo = logo; 
		this.resumes = resumes;
		this.loadingStatus = loadingStatus;
		this.bar = bar;
		this.searchRequest = searchRequest;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		logo.setVisibility(View.GONE);
		loadingStatus.setVisibility(View.VISIBLE);
		bar.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected String doInBackground(String... params) {
		BufferedReader bufferedReader = null;
		try {
			String uriGetResumesByRegions = "http://37.252.124.122/test/index.php/rest/getresumesbyregions?regions[]=";
			url = new URL(uriGetResumesByRegions + URLEncoder.encode(searchRequest, "UTF-8"));
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();		
			
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			jsonData = bufferedReader.readLine();
			
			Log.i(LOG_TAG, jsonData);
			
			jsonData = jsonData.substring(1, jsonData.length());
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			connection.disconnect();
		}// try/catch/finally
		
		return jsonData;
	}// doInBackground

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		/* JSON decoding */
		JSONParser parser = new JSONParser();
		try {
			JSONObject mainObject = (JSONObject)parser.parse(jsonData);
			JSONArray array = (JSONArray) mainObject.get("data");
			Iterator<List> iterator = array.iterator();
			
			while (iterator.hasNext()) {
				JSONObject object = (JSONObject) iterator.next();
				resumes.add(new Resume(
						object.get("title").toString(),
						object.get("description").toString(),
						object.get("publishing").toString(),
						object.get("link").toString(),
						object.get("site").toString(),
						object.get("city").toString()
					));// resumes.add
			}// while
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		loadingStatus.setVisibility(View.GONE);
		bar.setVisibility(View.GONE);
	}
}
