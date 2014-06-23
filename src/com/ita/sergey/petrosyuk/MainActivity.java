package com.ita.sergey.petrosyuk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.ita.sergey.petrosyuk.asynktasks.TaskCityField;
import com.ita.sergey.petrosyuk.listeners.ArrowButtonListener;

public class MainActivity extends ActionBarActivity {
	
	public static final String LOG_TAG = "myLogs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		
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
		
		private View view = null;
		private ImageButton imageButton;
		private Button buttonShowSearchBlock;
		private Button buttonInCity;
		private Button buttonInField;
		private int idCity;
		private int idField;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			
			this.setRetainInstance(true);
			view = rootView;
			imageButton = (ImageButton) view.findViewById(R.id.search_button);
			buttonShowSearchBlock = (Button) view.findViewById(R.id.close_open_search_block);
			buttonShowSearchBlock.setOnClickListener(new ArrowButtonListener(view));
			buttonInCity = (Button)view.findViewById(R.id.btn_in_city);
			buttonInField = (Button)view.findViewById(R.id.btn_in_field);
			buttonInCity.setOnClickListener(this);
			buttonInField.setOnClickListener(this);
			
			imageButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//new TaskCityField(view).execute(StaticData.GET_FIELDS, StaticData.FIELDS);					
				}// onClick
			});// setOnClickListener
			
			return rootView;
		}// onCreateView

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(view.getContext(), SelectCityOrFieldActivity.class);
			
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
		}// onClick
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(data == null)
				return;
			
			String name = data.getStringExtra(StaticData.NAME);				   
			int id 		= Integer.parseInt(data.getStringExtra(StaticData.ID));			
			
			if(requestCode == StaticData.REQUEST_CODE_FOR_CITY){
				name = name.substring(0, 1) + name.substring(1).toLowerCase();
				buttonInCity.setText(name);
				this.idCity   = id;
			} else {
				buttonInField.setText(name);
				this.idField   = id;
			}// if/else
			
		}// onActivityResult
	}// PlaceholderFragment
}// MainActivity
