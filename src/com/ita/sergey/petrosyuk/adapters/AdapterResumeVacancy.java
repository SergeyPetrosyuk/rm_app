package com.ita.sergey.petrosyuk.adapters;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ita.sergey.petrosyuk.MainActivity;
import com.ita.sergey.petrosyuk.R;
import com.ita.sergey.petrosyuk.StaticData;
import com.ita.sergey.petrosyuk.interfaces.ResumeVacancy;

public class AdapterResumeVacancy extends BaseAdapter {

	private List<ResumeVacancy> data;
	private Context context;
	
	public AdapterResumeVacancy(List<ResumeVacancy> data, Context context) {
		this.data 	 = data;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		if(view == null){
			view = LayoutInflater.from(context).inflate(R.layout.data_item, parent, false);
			ImageView image = (ImageView)view.findViewById(R.id.iv_item_icon);
			TextView title = (TextView)view.findViewById(R.id.tv_item_title);
			TextView site = (TextView)view.findViewById(R.id.tv_item_site);
			TextView date = (TextView)view.findViewById(R.id.tv_item_date);
			TextView city = (TextView)view.findViewById(R.id.tv_item_city);
			view.setTag(new Holder(image, title, site, date, city));
		}// if
		
		ResumeVacancy item = data.get(position);
		Holder holder = (Holder)view.getTag();
		int imgSrc = getIcon(item);
		String dateFull = item.getPublishing();
		String dateShort = dateFull.substring(0, 10);
		
		holder.title.setText(item.getTitle());
		holder.site.setText(item.getSite());
		holder.date.setText(dateShort);
		holder.city.setText(item.getCity());
		
		if(imgSrc <= 0){
			holder.image.setImageResource(R.drawable.default_icon);
		} else {
			holder.image.setImageResource(imgSrc);
		}// if/else
		
		return view;
	}// getView
	
	public int getIcon(ResumeVacancy item) {
		int src = 0;
		
		if(regEx(StaticData.REGEX_RABOTA, item.getSite()))
			src = R.drawable.rabota_ua;
		
		if(regEx(StaticData.REGEX_WORK, item.getSite()))
			src = R.drawable.work_ua_icon;
		
		if(regEx(StaticData.REGEX_JOBIK, item.getSite()))
			src = R.drawable.default_icon;
		
		if(regEx(StaticData.REGEX_JOBS, item.getSite()))
			src = R.drawable.jobs_ua_icon;
		
		return src;
	}// getIcon
	
	public boolean regEx(String ex, String src){
		Pattern p = Pattern.compile(ex);
		Matcher m = p.matcher(src);
		return m.matches();
	}// regEx
	
	public void setData(List<ResumeVacancy> data){
		this.data = data;
	}
	
	public List<ResumeVacancy> getData(){
		return this.data;
	}
	
	public class Holder{
		public final ImageView image;
		public final TextView title;
		public final TextView site;
		public final TextView date;
		public final TextView city;
		
		public Holder(ImageView image, TextView title, TextView site, TextView date, TextView city) {
			this.image = image;
			this.title = title;
			this.site = site;
			this.date = date;
			this.city = city;
		}
	}// Holder

}
