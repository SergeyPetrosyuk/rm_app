package com.ita.robotamolodi;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class DetailActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private TextView tvTitle;
		private TextView tvDescription;
		private TextView tvCity;
		private TextView tvSite;
		private TextView tvLink;
		private TextView tvPublishDate;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
			
			this.setRetainInstance(true);
			
			tvTitle = (TextView) rootView.findViewById(R.id.tv_title_detail);
			tvDescription = (TextView) rootView.findViewById(R.id.tv_description_detail);
			tvCity = (TextView) rootView.findViewById(R.id.tv_city_detail);
			tvSite = (TextView) rootView.findViewById(R.id.tv_site_detail);
			tvLink = (TextView) rootView.findViewById(R.id.tv_link_detail);
			tvPublishDate = (TextView) rootView.findViewById(R.id.tv_publish_date_detail);
			
			Intent intent = this.getActivity().getIntent();
			
			tvTitle.setText(intent.getStringExtra(MainActivity.EXTRA_TITLE));
			tvDescription.setText(intent.getStringExtra(MainActivity.EXTRA_DESCRIPTION));
			tvCity.setText(intent.getStringExtra(MainActivity.EXTRA_CITY));
			tvSite.setText(intent.getStringExtra(MainActivity.EXTRA_SITE));
			tvLink.setText(intent.getStringExtra(MainActivity.EXTRA_LINK));
			tvPublishDate.setText(intent.getStringExtra(MainActivity.EXTRA_PUBLISH_DATE));
			
			return rootView;
		}
	}

}
