package com.ita.sergey.petrosyuk.sqlite;

import java.io.File;
import java.io.IOException;

import android.content.Context;

public class CheckDB {
	private Context mContext;
	private DB db;
	private int isDbOK;
	
	public CheckDB(Context context) {
		mContext = context;
	}
	
	public boolean isDBExists(){
		
		File file = new File(mContext.getFilesDir(), "db_status.txt");
		
		if(file.exists()){
			db = new DB(mContext);
			db.open();
			isDbOK = db.getDBStatus();
			db.close();
			
			if(isDbOK > 0){
				return true;
			} else {
				return false;
			}			
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}// if/else
		
	}// isDBExists
}
