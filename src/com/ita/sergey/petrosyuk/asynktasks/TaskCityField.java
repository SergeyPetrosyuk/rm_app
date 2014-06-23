package com.ita.sergey.petrosyuk.asynktasks;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

import com.ita.sergey.petrosyuk.MainActivity;
import com.ita.sergey.petrosyuk.R;
import com.ita.sergey.petrosyuk.SearchBlock;
import com.ita.sergey.petrosyuk.SelectCityOrFieldActivity;
import com.ita.sergey.petrosyuk.adapters.AdapterCityField;
import com.ita.sergey.petrosyuk.interfaces.CityField;
import com.ita.sergey.petrosyuk.jsonParser.ParserJSONFactory;

public class TaskCityField extends AsyncTask<Object, Void, String> {

	private int dataType;
	/*private View v;
	private SearchBlock searchBlock = new SearchBlock();*/
	private Context context;
	private ListView listView;
	
	/*public TaskCityField(View view){
		this.v = view;
	}*/
	
	public TaskCityField(ListView listView, Context context){
		this.listView = listView;
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		//searchBlock.close(v, true);
	}

	@Override
	protected String doInBackground(Object... args) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		String jsonString = new Connection().getJSON((String) args[0]);
		dataType = (Integer)args[1];
		//List data = new ParserJSONFactory().getData(dataType, jsonString);
		return jsonString;
	}// doInBackground

	@Override
	protected void onPostExecute(String json) {
		super.onPostExecute(json);	

		ParserJSONFactory factory = new ParserJSONFactory();
		List<CityField> data = (List<CityField>) factory.getData(dataType, json);
		
		/*AdapterCityField adapterCityField = new AdapterCityField(data, v.getContext());
		ListView listView = (ListView)v.findViewById(R.id.lv_show_data);*/
		
		AdapterCityField adapterCityField = new AdapterCityField(data, context);
		listView.setAdapter(adapterCityField);	
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		//v.getContext().startActivity(new Intent(v.getContext(), SelectCityOrFieldActivity.class));
		//searchBlock.open(v, true);
	}// onPostExecute
}
