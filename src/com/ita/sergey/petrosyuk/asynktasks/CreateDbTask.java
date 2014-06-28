package com.ita.sergey.petrosyuk.asynktasks;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.view.View;

import com.ita.sergey.petrosyuk.SearchBlock;
import com.ita.sergey.petrosyuk.StaticData;
import com.ita.sergey.petrosyuk.interfaces.CityField;
import com.ita.sergey.petrosyuk.jsonParser.ParserJSONFactory;
import com.ita.sergey.petrosyuk.sqlite.DB;

public class CreateDbTask extends AsyncTask<Void, Void, Void> {
	
	private View v;
	private SearchBlock searchBlock = new SearchBlock();
	private DB db;
	private List<CityField> cityList;
	private List<CityField> fieldList;
	
	public CreateDbTask(View v) {
		this.v = v;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		searchBlock.close(v, true);
	}

	@Override
	protected Void doInBackground(Void... args) {

		cityList  = getData(StaticData.GET_CITIES, StaticData.CITIES);
		fieldList = getData(StaticData.GET_FIELDS, StaticData.FIELDS);
		db = new DB(v.getContext(), cityList, fieldList);
		db.open();
		db.close();
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		searchBlock.open(v, true);
	}
	
	private List<CityField> getData(String url, int dataType){
		String json = new Connection().getJSON(url);
		ParserJSONFactory factory = new ParserJSONFactory();
		List<CityField> data = (List<CityField>) factory.getData(dataType, json);	
		return data;
	}

}
