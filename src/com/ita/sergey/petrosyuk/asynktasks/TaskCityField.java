package com.ita.sergey.petrosyuk.asynktasks;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ita.sergey.petrosyuk.R;
import com.ita.sergey.petrosyuk.adapters.AdapterCityField;
import com.ita.sergey.petrosyuk.interfaces.CityField;
import com.ita.sergey.petrosyuk.sqlite.DB;

public class TaskCityField extends AsyncTask<Object, Void, List<CityField>> {

	private int dataType;
	private Context context;
	private ListView listView;
	private View v;
	private ProgressBar progressBar;
	
	public TaskCityField(View v, ListView listView, Context context){
		this.listView = listView;
		this.context = context;
		this.v = v;
		progressBar = (ProgressBar)v.findViewById(R.id.progress_bar_select_fragment);
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	protected List<CityField> doInBackground(Object... args) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		//String jsonString = new Connection().getJSON((String) args[0]);
		//dataType = (Integer)args[1];
		//List data = new ParserJSONFactory().getData(dataType, jsonString);
		
		DB db = new DB(context);
		db.open();
		List<CityField> list = db.getAllDataCityOrField((Integer) args[0]);
		db.close();
		return list;
	}// doInBackground

	@Override
	protected void onPostExecute(List<CityField> list) {
		super.onPostExecute(list);	
		/*ParserJSONFactory factory = new ParserJSONFactory();
		List<CityField> data = (List<CityField>) factory.getData(dataType, json);*/		
		AdapterCityField adapterCityField = new AdapterCityField(list, context);
		listView.setAdapter(adapterCityField);	
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		progressBar.setVisibility(View.GONE);
	}// onPostExecute
}
