package com.ita.sergey.petrosyuk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class WebViewActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);

		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_view, menu);
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
	}*/

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private WebView webView;
		private ProgressBar progressBar;
		private ImageButton buttonBack;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
			
			buttonBack = (ImageButton)rootView.findViewById(R.id.button_move_back);
			progressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
			webView = (WebView)rootView.findViewById(R.id.web_view_for_list_item);
			webView.setWebViewClient(new MyWebClient());
			webView.getSettings().setJavaScriptEnabled(true);
			
			buttonBack.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					getActivity().finish();
				}
			});
			
			Intent intent = getActivity().getIntent();
			String url = intent.getStringExtra(StaticData.URL);
			
			webView.loadUrl(url);
			
			return rootView;
		}
		
		public class MyWebClient extends WebViewClient{
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				progressBar.setVisibility(View.GONE);
			}
			
		}// MyWebClient
	}

}
