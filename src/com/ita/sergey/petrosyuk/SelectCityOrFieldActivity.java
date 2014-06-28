package com.ita.sergey.petrosyuk;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ita.sergey.petrosyuk.adapters.AdapterCityField;
import com.ita.sergey.petrosyuk.asynktasks.TaskCityField;
import com.ita.sergey.petrosyuk.interfaces.CityField;
import com.ita.sergey.petrosyuk.sqlite.DB;

public class SelectCityOrFieldActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_city_or_field);

		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.select_city_or_field, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {

		private ListView lvCitiesOrFields; 
		private EditText etSearchLine; 
		private List<CityField> dataCitiesOrFields;
		private Button buttonDone;
		private Pattern p = Pattern.compile("[\\w]*");
		private Matcher m;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_select_city_or_field, container, false);
			
			this.setRetainInstance(true);
			
			buttonDone = (Button) rootView.findViewById(R.id.button_done);
			buttonDone.setOnClickListener(this);
			
			lvCitiesOrFields = (ListView)rootView.findViewById(R.id.lv_select_city_or_field);
			etSearchLine = (EditText)rootView.findViewById(R.id.et_search_city_or_field);
			
			
			etSearchLine.addTextChangedListener(new TextWatcher() {
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					AdapterCityField adapter = (AdapterCityField) lvCitiesOrFields.getAdapter();
					List<CityField> tmpList = new ArrayList<CityField>();
					
					m = p.matcher(etSearchLine.getText());
					
					if(m.matches()){
						Log.i(MainActivity.LOG_TAG, "" + true);
					} else {
						Log.i(MainActivity.LOG_TAG, "" + false);
						return;
					}// if
					
					if(count > 0){
						
						if(dataCitiesOrFields == null)
							dataCitiesOrFields = adapter.getData();
						
						String sub = null;
						
						for(CityField item : dataCitiesOrFields){
							String name = item.getName().toLowerCase();
							String letters = etSearchLine.getText().toString().toLowerCase();
							
							if(name.length() >= letters.length()){
								sub = name.substring(start, count);
								if(sub.equals(letters)){
									tmpList.add(item);
								}// if
							}// if

						}// for
						
						adapter.setData(tmpList);
						
					} else if(count < 0){
						adapter.setData(dataCitiesOrFields);
					}// else/if
					
					adapter.notifyDataSetChanged();
				}// onTextChanged
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
				}
			});// etSearchLine onTextChanged()
			
			Intent intent = this.getActivity().getIntent();
			Integer dataType = intent.getIntExtra("data_type", StaticData.CITIES);
			
			switch (dataType) {
			case StaticData.CITIES:
				new TaskCityField(rootView, lvCitiesOrFields, this.getActivity()).execute(DB.TABLE_CITY);
				break;
			case StaticData.FIELDS:
				new TaskCityField(rootView, lvCitiesOrFields, this.getActivity()).execute(DB.TABLE_FIELD);
				break;
			default:
				break;
			}// switch
			
			return rootView;
		}// onCreateView		
			
		@Override
		public void onClick(View v) {
			AdapterCityField adapterForResult = (AdapterCityField) lvCitiesOrFields.getAdapter();
			int itemPosition = lvCitiesOrFields.getCheckedItemPosition();
			
			if(itemPosition != -1){
				List<CityField> tmp = adapterForResult.getData();
				CityField item = tmp.get(itemPosition);		
				Intent data = new Intent();
				data.putExtra(StaticData.ID, item.getIntId());
				data.putExtra(StaticData.NAME, item.getName());
				this.getActivity().setResult(RESULT_OK, data);
			} else {
				this.getActivity().setResult(RESULT_OK, null);
			}// if/else
			
			this.getActivity().finish();
		}// onClick
	}// PlaceholderFragment
}// SelectCityOrFieldActivity
