package com.ita.sergey.petrosyuk.asynktasks;

import java.util.List;
import java.util.regex.*;

import android.R;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ita.sergey.petrosyuk.MainActivity;
import com.ita.sergey.petrosyuk.SearchBlock;
import com.ita.sergey.petrosyuk.StaticData;
import com.ita.sergey.petrosyuk.adapters.AdapterResumeVacancy;
import com.ita.sergey.petrosyuk.entity.resume.Resume;
import com.ita.sergey.petrosyuk.entity.vacancy.Vacancy;
import com.ita.sergey.petrosyuk.interfaces.ResumeVacancy;
import com.ita.sergey.petrosyuk.jsonParser.ParserJSONFactory;

public class TaskResumeVacancy extends AsyncTask<Object, Void, String> {
	
	private int dataType;
	private View v;
	private ListView listView;
	private Context context;
	private SearchBlock searchBlock = new SearchBlock();
	
	public TaskResumeVacancy(View view, ListView listView, Context context){
		this.v = view;
		this.listView = listView;
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		searchBlock.close(v, true);
	}// onPreExecute

	@Override
	protected String doInBackground(Object... args) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		String jsonString = new Connection().getJSON((String) args[0]);
		dataType = (Integer)args[1];		
		return jsonString;
	}// doInBackground
	
	@Override
	protected void onPostExecute(String json) {
		super.onPostExecute(json);
		
		ParserJSONFactory factory = new ParserJSONFactory();
		List<ResumeVacancy> list = (List<ResumeVacancy>) factory.getData(dataType, json);
		
		if((list != null) && (list.size() > 0)){
			AdapterResumeVacancy adapter = new AdapterResumeVacancy(list, context);
			listView.setAdapter(adapter);
		} else {
			AdapterResumeVacancy adapterResumeVacancy = (AdapterResumeVacancy) listView.getAdapter();
			
			if(adapterResumeVacancy == null){
				Toast.makeText(context, com.ita.sergey.petrosyuk.R.string.nothing_found, Toast.LENGTH_SHORT).show();
			} else {
				int size = adapterResumeVacancy.getData().size();
				if(size > 0){
					adapterResumeVacancy.getData().clear();
					adapterResumeVacancy.notifyDataSetChanged();
				} else {
					Toast.makeText(context, com.ita.sergey.petrosyuk.R.string.nothing_found, Toast.LENGTH_SHORT).show();
				}// else
			}// else
		}// else
		
		searchBlock.open(v, true);
	}// onPostExecute
	
	

}
