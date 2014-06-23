package com.ita.sergey.petrosyuk.listeners;

import com.ita.sergey.petrosyuk.R;
import com.ita.sergey.petrosyuk.SearchBlock;
import com.ita.sergey.petrosyuk.StaticData;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ArrowButtonListener implements OnClickListener {

	private View viewGroup;
	private SearchBlock block = new SearchBlock();
	
	public ArrowButtonListener(View viewGroup) {
		this.viewGroup = viewGroup;
	}
	
	@Override
	public void onClick(View v) {
		if(StaticData.ARROW_STATUS == StaticData.DEATIL_SEARCH_OPENED){
			block.close(viewGroup, false);
			StaticData.ARROW_STATUS = StaticData.DEATIL_SEARCH_CLOSED;
			((Button)v).setCompoundDrawablesWithIntrinsicBounds(R.drawable.open_arrow, 0, 0, 0);
		} else {
			block.open(viewGroup, false);
			StaticData.ARROW_STATUS = StaticData.DEATIL_SEARCH_OPENED;
			((Button)v).setCompoundDrawablesWithIntrinsicBounds(R.drawable.close_arrow, 0, 0, 0);
		}// if/else
	}

}
