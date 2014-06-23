package com.ita.sergey.petrosyuk;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class SearchBlock {

	public void close(View v, boolean progressBar){
		
		if(progressBar){
			((RelativeLayout)v.findViewById(R.id.rl_progress_bar)).setVisibility(View.VISIBLE);
			((ListView)v.findViewById(R.id.lv_show_data)).setVisibility(View.GONE);
			((Button)v.findViewById(R.id.close_open_search_block)).setVisibility(View.GONE);
		}
		
		((LinearLayout)v.findViewById(R.id.ll_search_block)).setVisibility(View.GONE);
	}
	
	public void open(View v, boolean progressBar){
		
		if(progressBar){
			((RelativeLayout)v.findViewById(R.id.rl_progress_bar)).setVisibility(View.GONE);
			((ListView)v.findViewById(R.id.lv_show_data)).setVisibility(View.VISIBLE);
			((Button)v.findViewById(R.id.close_open_search_block)).setVisibility(View.VISIBLE);
		}
		
		((LinearLayout)v.findViewById(R.id.ll_search_block)).setVisibility(View.VISIBLE);
	}
	
}
