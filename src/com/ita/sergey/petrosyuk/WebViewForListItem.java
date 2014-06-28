package com.ita.sergey.petrosyuk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewForListItem extends Activity {
	
	private WebView webView;
	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view);
		
		progressBar = (ProgressBar)findViewById(R.id.progress_bar);
		webView = (WebView)findViewById(R.id.web_view_for_list_item);
		webView.setWebViewClient(new MyWebClient());
		webView.getSettings().setJavaScriptEnabled(true);
		
		Intent intent = getIntent();
		String url = intent.getStringExtra(StaticData.URL);
		
		webView.loadUrl(url);
	}// onCreate
	
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
