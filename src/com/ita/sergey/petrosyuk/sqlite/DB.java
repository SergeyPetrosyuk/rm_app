package com.ita.sergey.petrosyuk.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.ita.sergey.petrosyuk.entity.city.City;
import com.ita.sergey.petrosyuk.entity.resume.Resume;
import com.ita.sergey.petrosyuk.interfaces.CityField;
import com.ita.sergey.petrosyuk.interfaces.ResumeVacancy;

import android.R;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DB {
	private static final String DB_NAME = "rm";
	private static final int DB_VERSION = 1;
	
	public static final String DB_TABLE_CITY     = "cities";
	public static final String DB_TABLE_FIELD    = "fields";
	public static final String DB_TABLE_FAVORITE = "favorite";
	
	public static final int TABLE_CITY     = 0;
	public static final int TABLE_FIELD    = 1;
	public static final int TABLE_FAVORITE = 2;

	// Columns for City and Field tables
	public static final String DB_CF_COLUMN_ID   = "_id";
	public static final String DB_CF_COLUMN_NAME = "name";
	
	//Columns for a Favorite table
	public static final String DB_F_COLUMN_ID 					= "_id";
	public static final String DB_F_COLUMN_TITLE 				= "title";
	public static final String DB_F_COLUMN_LOWER_LIMIT_SALARY 	= "lower_limit_salary";
	public static final String DB_F_COLUMN_UPPER_LIMIT_SALARY 	= "upper_limit_salary";
	public static final String DB_F_COLUMN_DESCRIPTION 			= "description";
	public static final String DB_F_COLUMN_PUBLISHING 			= "publishing";
	public static final String DB_F_COLUMN_EMPLOYER 			= "employer";
	public static final String DB_F_COLUMN_CITY 				= "city";
	public static final String DB_F_COLUMN_LINK 				= "link";
	public static final String DB_F_COLUMN_SITE 				= "site";
	public static final String DB_F_COLUMN_FIELD 				= "field";
	
	public static final String DB_CREATE_CITY =
			"create table " + DB_TABLE_CITY + "(" +
						DB_CF_COLUMN_ID + " integer, " + 
						DB_CF_COLUMN_NAME + " text" + 
					");";
	
	public static final String DB_CREATE_FIELD = 
			"create table " + DB_TABLE_FIELD + "(" +
					DB_CF_COLUMN_ID + "  integer, " +
					DB_CF_COLUMN_NAME + " text" +
				");";

	public static final String DB_CREATE_FAVORITE = 
			"create table " + DB_TABLE_FAVORITE + "(" +
					DB_F_COLUMN_ID + " integer primary key autoincrement, " +
					DB_F_COLUMN_TITLE + " text, " +
					DB_F_COLUMN_CITY + " text, " +
					DB_F_COLUMN_DESCRIPTION + " text, " +
					DB_F_COLUMN_FIELD + " text, " +
					DB_F_COLUMN_EMPLOYER + " text, " + 
					DB_F_COLUMN_SITE + " text, " +
					DB_F_COLUMN_LINK + " text, " +
					DB_F_COLUMN_PUBLISHING + " text, " +
					DB_F_COLUMN_LOWER_LIMIT_SALARY + " text, " +
					DB_F_COLUMN_UPPER_LIMIT_SALARY + " text " + 
				");";
	
	public static final String DB_CREATE_STATUS = 
			"create table status(_id integer primary key default 1, is_ok integer);";
	
	private final Context context;
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	
	private List<CityField> cities;
	private List<CityField> fields;
	
	public DB(Context context, List<CityField> cities, List<CityField> fields) {
		this.context = context;
		this.cities = cities;
		this.fields = fields;
	}// constructor
	
	public DB(Context context){
		this.context = context;
	}
	
	// Open a DB connection
	public void open(){
		dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION, cities, fields);
		db = dbHelper.getWritableDatabase();
	}// open
	
	// Close a DB connection
	public void close(){
		if(dbHelper != null)
			dbHelper.close();
	}// close
	
	// Get all data from table
	public List<CityField> getAllDataCityOrField(int table){
		List<CityField> data = null;
		Cursor cursor = null;
		
		switch (table) {
			case TABLE_CITY:
				cursor = db.query(DB_TABLE_CITY, null, null, null, null, null, null);
				data = convertCursorToList(cursor);
				break;
			case TABLE_FIELD:
				cursor = db.query(DB_TABLE_FIELD, null, null, null, null, null, null);
				data = convertCursorToList(cursor);
				break;
			default:
				break;
		}// switch
		
		return data;
	}// getAllData
	
	public List<ResumeVacancy> getAllFavorites(){
		List<ResumeVacancy> data = null;
		Cursor cursor = null;
		
		cursor = db.query(DB_TABLE_FAVORITE, null, null, null, null, null, null);
		
		if(cursor != null){
			if(cursor.moveToFirst()){
				data = new ArrayList<ResumeVacancy>();
				do{
					int colIndexId    = cursor.getColumnIndex(DB_F_COLUMN_ID);
					int colIndexTitle = cursor.getColumnIndex(DB_F_COLUMN_TITLE);
					int colIndexDesc  = cursor.getColumnIndex(DB_F_COLUMN_DESCRIPTION);
					int colIndexCity  = cursor.getColumnIndex(DB_F_COLUMN_CITY);
					int colIndexLlSal = cursor.getColumnIndex(DB_F_COLUMN_LOWER_LIMIT_SALARY);
					int colIndexUlSal = cursor.getColumnIndex(DB_F_COLUMN_UPPER_LIMIT_SALARY);
					int colIndexEmp   = cursor.getColumnIndex(DB_F_COLUMN_EMPLOYER);
					int colIndexPub   = cursor.getColumnIndex(DB_F_COLUMN_PUBLISHING);
					int cilIndexLink  = cursor.getColumnIndex(DB_F_COLUMN_LINK);
					int colIndexSite  = cursor.getColumnIndex(DB_F_COLUMN_SITE);
					int colIndexField = cursor.getColumnIndex(DB_F_COLUMN_FIELD);
					
					ResumeVacancy row = new Resume();
					row.setId(cursor.getInt(colIndexId));
					row.setTitle(cursor.getString(colIndexTitle));
					row.setDescription(cursor.getString(colIndexDesc));
					row.setCity(cursor.getString(colIndexCity));
					row.setSite(cursor.getString(colIndexSite));
					row.setLink(cursor.getString(cilIndexLink));
					row.setPublishing(cursor.getString(colIndexPub));
					row.setField(cursor.getString(colIndexField));
					row.setEmployer(cursor.getString(colIndexEmp));
					row.setLower_limit_salary(cursor.getString(colIndexLlSal));
					row.setUpper_limit_salary(cursor.getString(colIndexUlSal));
					
					data.add(row);
				}while(cursor.moveToNext());
			}// if
		}// if
		
		return data;
	}// getAllFavorites
	
	public void delRecord(String id){
		db.delete(DB_TABLE_FAVORITE, DB_F_COLUMN_ID + " = '" + id + "'", null);
	}// delRecord
	
	public int getDBStatus(){
		int isOk = 0;
		Cursor cursor;
		cursor = db.query("status", null, null, null, null, null, null);
		
		if(cursor != null){
			if(cursor.moveToFirst()){
				int colIndex = cursor.getColumnIndex("is_ok");
				isOk = cursor.getInt(colIndex);
			}
		}
		
		return isOk;
	}// getDBStatus
	
	private List<CityField> convertCursorToList(Cursor cursor){
		List<CityField> list = new ArrayList<CityField>();
		
		if(cursor != null){
			if(cursor.moveToFirst()){
				do{
					int colIndexId   = cursor.getColumnIndex(DB_CF_COLUMN_ID);
					int colIndexName = cursor.getColumnIndex(DB_CF_COLUMN_NAME);
					
					CityField row = new City();
					row.setIntId(cursor.getInt(colIndexId));
					row.setName(cursor.getString(colIndexName));
					list.add(row);
				}while(cursor.moveToNext());
			} else {
				Toast.makeText(context, com.ita.sergey.petrosyuk.R.string.no_records_in_db, Toast.LENGTH_SHORT).show();
			}// if
		}// if
		
		return list;
	}// convertCursorToList
}// DB

