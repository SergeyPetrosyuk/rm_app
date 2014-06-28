package com.ita.sergey.petrosyuk;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ita.sergey.petrosyuk.asynktasks.CreateDbTask;
import com.ita.sergey.petrosyuk.asynktasks.TaskResumeVacancy;
import com.ita.sergey.petrosyuk.interfaces.ResumeVacancy;
import com.ita.sergey.petrosyuk.listeners.ArrowButtonListener;
import com.ita.sergey.petrosyuk.sqlite.CheckDB;

public class MainActivity extends ActionBarActivity {
	
	public static final String LOG_TAG = "myLogs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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
		
		private Spinner spinnerVacancyOrResume;
		private ArrayAdapter<String> adapterSpinner;
		private View view = null;
		private ImageButton imageButton;
		private Button buttonShowSearchBlock;
		private Button buttonInCity;
		private Button buttonInField;
		private ImageButton buttonClearCity;
		private ImageButton buttonClearField;
		private EditText etSearchLine;
		private ListView listViewMain;
		private int idCity;
		private int idField;

				
		public PlaceholderFragment() {
			
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			
			this.setRetainInstance(true);
			
			idCity = -1;
			idField = -1;
			
			view = rootView;
			
			listViewMain = (ListView)view.findViewById(R.id.lv_show_data);
			etSearchLine = (EditText) view.findViewById(R.id.et_search_line);
			spinnerVacancyOrResume = (Spinner)view.findViewById(R.id.spiner_vacancy_or_resume);
			imageButton = (ImageButton) view.findViewById(R.id.search_button);
			buttonShowSearchBlock = (Button) view.findViewById(R.id.close_open_search_block);
			buttonShowSearchBlock.setOnClickListener(new ArrowButtonListener(view));
			buttonInCity = (Button)view.findViewById(R.id.btn_in_city);
			buttonInField = (Button)view.findViewById(R.id.btn_in_field);
			buttonInCity.setOnClickListener(this);
			buttonInField.setOnClickListener(this);
			
			buttonClearCity = (ImageButton)view.findViewById(R.id.button_clear_city);
			buttonClearField = (ImageButton)view.findViewById(R.id.button_clear_field);
			
			String[] dataForSpinner = view.getResources().getStringArray(R.array.vacancy_or_resume);
			int spinnerLayout = R.layout.spinner_item;
			adapterSpinner = new ArrayAdapter<String>(view.getContext(), spinnerLayout, dataForSpinner);
			adapterSpinner.setDropDownViewResource(R.layout.spinner_drop_down);
			spinnerVacancyOrResume.setAdapter(adapterSpinner);
					
			if(new CheckDB(getActivity()).isDBExists()){
				
			} else {
				if(new ConnectionDetector(getActivity().getApplicationContext()).isConnectingToInternet()){
					new CreateDbTask(rootView).execute();
				} else {
					Toast.makeText(getActivity(), R.string.check_connection, Toast.LENGTH_SHORT).show();
				}
			}
			
			imageButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int categoryId = (int) spinnerVacancyOrResume.getSelectedItemId();
					
					String category = categoryId + "";
					String field = "";
					String city  = "";
					
					if(idField > -1)
						field = idField + "";
					
					if(idCity > -1)
						city = idCity + "";
					
					String phrase = null;
					
					try {
						phrase = URLEncoder.encode(etSearchLine.getText().toString(), "UTF-8");
					} catch (UnsupportedEncodingException e) { }
					
					String url = StaticData.GET_SEARCHED_DATA + "?category=" + category + "&field=" + field + ""
							+ "&letters=" + phrase + "&city=" + city;
					
					if(new ConnectionDetector(getActivity()).isConnectingToInternet()){
						if(categoryId == StaticData.SPINNER_RESUME){
							new TaskResumeVacancy(view, listViewMain, getActivity()).execute(url, StaticData.RESUMES);
						} else {
							new TaskResumeVacancy(view, listViewMain, getActivity()).execute(url, StaticData.VACANCIES);
						}// if/else
					} else {
						Toast.makeText(getActivity(), R.string.check_connection, Toast.LENGTH_SHORT).show();
						return;
					}// if/else				
					
				}// onClick
			});// setOnClickListener
			
			listViewMain.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
					if(new ConnectionDetector(getActivity()).isConnectingToInternet()){
						ResumeVacancy item = (ResumeVacancy) adapter.getItemAtPosition(position);
						String url = item.getLink();
						
						Intent intent = new Intent(getActivity(), WebViewActivity.class);
						intent.putExtra(StaticData.URL, url);
						startActivity(intent);
					} else {
						Toast.makeText(getActivity(), R.string.check_connection, Toast.LENGTH_SHORT).show();
						return;
					}// if/else	
					
				}// onItemClick
				
			});// listViewMain.setOnItemClickListener
			
			buttonClearCity.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonInCity.setText(R.string.make_choice);
					buttonClearCity.setVisibility(View.GONE);
					idCity = 0;
				}
			});// buttonClearCity.setOnClickListener
			
			buttonClearField.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonInField.setText(R.string.make_choice);
					buttonClearField.setVisibility(View.GONE);
					idField = 0;
				}
			});// buttonClearField.setOnClickListener
			
			return rootView;
		}// onCreateView

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(view.getContext(), SelectCityOrFieldActivity.class);
			
			if(new ConnectionDetector(getActivity().getApplicationContext()).isConnectingToInternet()){
				switch (v.getId()) {
				case R.id.btn_in_city:
					intent.putExtra("data_type", StaticData.CITIES);
					startActivityForResult(intent, StaticData.REQUEST_CODE_FOR_CITY);
					break;
				case R.id.btn_in_field:
					intent.putExtra("data_type", StaticData.FIELDS);
					startActivityForResult(intent, StaticData.REQUEST_CODE_FOR_FIELD);
					break;
				default:
					break;
				}//switch
			} else {
				Toast.makeText(getActivity(), R.string.check_connection, Toast.LENGTH_SHORT).show();
				return;
			}// if/else
			
		}// onClick
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(data == null)
				return;
			
			String name = data.getStringExtra(StaticData.NAME);				   
			int id 		= data.getIntExtra(StaticData.ID, -1);
			
			if(requestCode == StaticData.REQUEST_CODE_FOR_CITY){
				name = name.substring(0, 1) + name.substring(1).toLowerCase();
				buttonInCity.setText(name);
				this.idCity   = id;
				buttonClearCity.setVisibility(View.VISIBLE);
			} else {
				buttonInField.setText(name);
				this.idField   = id;
				buttonClearField.setVisibility(View.VISIBLE);
			}// if/else
			
		}// onActivityResult
		
		
	}// PlaceholderFragment
}// MainActivity
