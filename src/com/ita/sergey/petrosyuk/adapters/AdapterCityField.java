package com.ita.sergey.petrosyuk.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ita.sergey.petrosyuk.entity.city.City;
import com.ita.sergey.petrosyuk.interfaces.CityField;

public class AdapterCityField extends BaseAdapter{

	private List<CityField> data;
	private Context context;
	
	public AdapterCityField(List<CityField> data, Context context) {
		this.data = data;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view == null){
			view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_single_choice, parent, false);
			TextView textView = (TextView) view.findViewById(android.R.id.text1);
			view.setTag(new Holder(textView));
			view.setSelected(true);
		}// if
		
		Holder holder = (Holder) view.getTag();
		holder.textView.setText(data.get(position).getName());
		
		return view;
	}
	
	public void setData(List<CityField> data){
		this.data = data;
	}
	
	public List<CityField> getData(){
		return data;
	}
	
	public class Holder{
		public final TextView textView;
		public Holder(TextView textView){
			this.textView = textView;
		}
	}// Holder

}
