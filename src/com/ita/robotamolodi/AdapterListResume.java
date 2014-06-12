package com.ita.robotamolodi;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListResume extends BaseAdapter {
	private Context context;
	private List<Resume> resumes;
	
	public AdapterListResume(Context context, ArrayList<Resume> resumes) {
		this.context = context;
		this.resumes = resumes;
	}// constructor
	
	public AdapterListResume() {
	}

	@Override
	public int getCount() {
		return resumes.size();
	}

	@Override
	public Object getItem(int position) {
		return resumes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.resume_item, parent, false);
			
			TextView employee = (TextView) convertView.findViewById(R.id.tv_item_employee);
			TextView siteSource = (TextView) convertView.findViewById(R.id.tv_item_site);
			
			convertView.setTag(new ViewHolder(employee, siteSource));
		}// if
		
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.employee.setText(resumes.get(position).title);
		holder.siteSource.setText(resumes.get(position).site);
		
		return convertView;
	}// getView
	
	public class ViewHolder{
		public final TextView employee;
		public final TextView siteSource;
		
		public ViewHolder(TextView employee, TextView siteSource) {
			this.employee = employee;
			this.siteSource = siteSource;
		}
	}// ViewHolder

	public void setContext(Context context){
		this.context = context;
	}// setContext
	
	public void setData(List<Resume> data){
		this.resumes = data;
	}// setData
}
