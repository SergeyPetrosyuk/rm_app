package com.ita.robotamolodi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	public static final String EXTRA_TITLE 		  = "title";
	public static final String EXTRA_DESCRIPTION  = "description";
	public static final String EXTRA_PUBLISH_DATE =  "publish";
	public static final String EXTRA_LINK 		  = "link";
	public static final String EXTRA_SITE 		  = "site";
	public static final String EXTRA_CITY 		  = "city";
	public static int DEATIL_SEARCH_OPENED 		  = 1;
	public static int DEATIL_SEARCH_CLOSED 		  = 0;
	public static Integer ARROW_STATUS 			  = 1;		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
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
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {
		
		private ImageView logo;
		private Spinner spinnerVacancyOrResume;
		private LinearLayout 		layoutDetailSearching;
		private Button 		 		buttonArrow;
		
		private EditText 			editTextSearchLine;
		private ImageButton 		buttonSearch;
		private Button 				buttonSelectCity;
		private Button 				buttonSelectField;
		private ListView 			listView;
		private ProgressBar 		progressBar;
		private TextView 			textViewLoading;
		private AdapterListResume 	adapter;
		private RestTask 			task;
		private List<Resume> 		resumes = null;
		private ArrayAdapter<String> adapterSpinner;
		
		private OnClickListener 	searchButtonListener;
		private OnClickListener		arrowButtonListener;
		private OnItemClickListener itemClickListenerListView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			this.setRetainInstance(true);
			prepareView(rootView);
			
			return rootView;
		}// onCreateView

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btn_in_city:
					Toast.makeText(getActivity(), "city", Toast.LENGTH_SHORT).show();
					break;
				case R.id.btn_in_field:
					Toast.makeText(getActivity(), "fiels", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
			}// switch
		}// onClick
		
		@Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        LayoutInflater inflater = LayoutInflater.from(getActivity());
	        populateViewForOrientation(inflater, (ViewGroup) getView());
	    }

	    private void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
	    	ArrayAdapter<String> spinerAdapterTmp = this.adapterSpinner;
	    	int choisenItemSpiner = spinnerVacancyOrResume.getSelectedItemPosition();
	    	Integer arrrowStatusTmp = (Integer) buttonArrow.getTag();

	    	ArrayList<Resume> resumesTmp = new ArrayList<Resume>();
	    	resumesTmp.addAll(resumes);
	    	
	        viewGroup.removeAllViewsInLayout();
	        View subview = inflater.inflate(R.layout.fragment_main, viewGroup);
	        
	        prepareView(subview);
	        
	        this.resumes = resumesTmp;
	        this.adapter = new AdapterListResume(getActivity(), (ArrayList<Resume>) resumes);
	        this.listView.setAdapter(this.adapter);
	        this.listView.setOnItemClickListener(itemClickListenerListView);
	        
	        this.spinnerVacancyOrResume.setSelection(choisenItemSpiner);
	        this.buttonArrow.setTag(arrrowStatusTmp);
	        	        
	        if(arrrowStatusTmp == DEATIL_SEARCH_OPENED){
	        	layoutDetailSearching.setVisibility(View.VISIBLE);
	        	buttonArrow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.close_arrow, 0, 0, 0);
	        } else {
	        	layoutDetailSearching.setVisibility(View.GONE);
	        	buttonArrow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.open_arrow, 0, 0, 0);
			}// if/else
	        
	        if(resumes.size() > 0){
	        	this.logo.setVisibility(View.GONE);
	        }// if
	    }
		
		private void prepareView(View rootView){
			logo 					= (ImageView) rootView.findViewById(R.id.logo);
			spinnerVacancyOrResume 	= (Spinner)rootView.findViewById(R.id.spiner_vacancy_or_resume);
			buttonSelectCity 		= (Button) rootView.findViewById(R.id.btn_in_city);
			buttonSelectField 		= (Button) rootView.findViewById(R.id.btn_in_field);
			
			buttonSelectCity.setOnClickListener(this);
			buttonSelectField.setOnClickListener(this);
			
			buttonArrow 		= (Button) rootView.findViewById(R.id.b_close_open_detail_searching);
			buttonArrow.setTag(ARROW_STATUS);
			
			layoutDetailSearching  	= (LinearLayout) rootView.findViewById(R.id.ll_detail_searching);
			editTextSearchLine 		= (EditText) rootView.findViewById(R.id.et_search_line);
			buttonSearch 			= (ImageButton) rootView.findViewById(R.id.search_button);
			textViewLoading 		= (TextView) rootView.findViewById(R.id.tv_waiting);
			progressBar 			= (ProgressBar) rootView.findViewById(R.id.progress_bar);
			listView 				= (ListView) rootView.findViewById(R.id.list_view);
			
			resumes = new ArrayList<Resume>();
			
			String[] spinnerItems = rootView.getResources().getStringArray(R.array.vacancy_or_resume);
			adapterSpinner = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item_selected,spinnerItems);
			adapterSpinner.setDropDownViewResource(R.layout.spinner_item);
			spinnerVacancyOrResume.setAdapter(adapterSpinner);
			
			arrowButtonListener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					checkArrowStatus(v);
				}// onClick
			};// arrowButtonListener
			
			buttonArrow.setOnClickListener(arrowButtonListener);
									
			searchButtonListener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					adapter = new AdapterListResume();
					String searchReques = editTextSearchLine.getText().toString();
					
					if(new ConnectionDetector(getActivity().getApplicationContext()).isConnectingToInternet()){
						task = new RestTask(resumes, textViewLoading, progressBar, logo, searchReques);
						task.execute();
					} else {
						return;
					}// if a phone is connected to internet
					
					if(resumes != null){
						adapter.setContext(getActivity());
						adapter.setData(resumes);
						listView.setAdapter(adapter);
						
						itemClickListenerListView = new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
								Resume resume = (Resume) adapter.getItem(position);
								
								Intent intent = new Intent(getActivity(), DetailActivity.class);
								
								intent.putExtra(EXTRA_TITLE, resume.title);
								intent.putExtra(EXTRA_PUBLISH_DATE, resume.pubDate);
								intent.putExtra(EXTRA_DESCRIPTION, resume.description);
								intent.putExtra(EXTRA_CITY, resume.city);
								intent.putExtra(EXTRA_SITE, resume.site);
								intent.putExtra(EXTRA_LINK, resume.link);
								
								startActivity(intent);
							}// onItemClick
						};// itemClickListenerListView
						
						listView.setOnItemClickListener(itemClickListenerListView);
					} else {
						Toast.makeText(getActivity(), R.string.check_connection, Toast.LENGTH_SHORT).show();
					}// if/else
				}// onClick
			};// searchButtonListener
			
			buttonSearch.setOnClickListener(searchButtonListener);
		}// prepareView
		
		private void checkArrowStatus(View v){
			Integer arrowStatus = (Integer) v.getTag();
			if(arrowStatus == DEATIL_SEARCH_OPENED){
				arrowStatus = DEATIL_SEARCH_CLOSED;
				buttonArrow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.open_arrow, 0, 0, 0);
				layoutDetailSearching.setVisibility(View.GONE);
			} else {
				arrowStatus = DEATIL_SEARCH_OPENED;
				buttonArrow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.close_arrow, 0, 0, 0);
				layoutDetailSearching.setVisibility(View.VISIBLE);
			}// if/else
			
			buttonArrow.setTag(arrowStatus);
		}// checkArrowStatus
	}// PlaceholderFragment

}
