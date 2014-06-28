package com.ita.sergey.petrosyuk.sqlite;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ita.sergey.petrosyuk.interfaces.CityField;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	private List<CityField> cities;
	private List<CityField> fields;

	public DBHelper(Context context, String name, CursorFactory factory, int version,
			List<CityField> cities, List<CityField> fields) {
		super(context, name, factory, version);
		
		this.cities = cities;
		this.fields = fields;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB.DB_CREATE_CITY);
		db.execSQL(DB.DB_CREATE_FIELD);
		db.execSQL(DB.DB_CREATE_FAVORITE);
		db.execSQL(DB.DB_CREATE_STATUS);
		db.execSQL("insert into status(_id, is_ok) values(1, 0)");
		addCityOrField(DB.DB_TABLE_CITY, cities, db);
		addCityOrField(DB.DB_TABLE_FIELD, fields, db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

	public void addCityOrField(String table, List<CityField> list, SQLiteDatabase db){
		String sqlPattern = "INSERT INTO " + table + " ("+ DB.DB_CF_COLUMN_ID +", "+ DB.DB_CF_COLUMN_NAME +") VALUES";
		String sql = sqlPattern;
		CityField item;
		Pattern p;
		Matcher m;
		
		db.beginTransaction();
		for(int i = 0, j = 0; i < list.size(); i++, j++){
			item = list.get(i);
			String name = item.getName();
			String values = "";
			
			p = Pattern.compile("'");
			m = p.matcher(name);
			
			if (m.find()){
				name = name.replace("'", "`");
			}
			
			values = "('" + item.getId() + "', '" + name + "')";
			
			db.execSQL(sql+values);
			
			
			/*if(j > 25){
				try{
					db.beginTransaction();
					db.execSQL(sql);
					db.setTransactionSuccessful();
				} catch (Exception e){
					
				} finally {
					db.endTransaction();
					db.execSQL("update status set is_ok = 1 where _id = 1;");
				}
				sql = sqlPattern;
				j = 0;
			}
			
			item = list.get(i);
			String name = item.getName();
			
			p = Pattern.compile("'");
			m = p.matcher(name);
			
			if (m.find()){
				name = name.replace("'", "`");
			}
			
			if(i == 0){
				sql += "('" + item.getId() + "', '" + name + "')";
			} else if(i == list.size()-1) {
				sql += ", (" + item.getId() + ", '" + name + "')";
			} else {
				if(j == 0)
					sql += "(" + item.getId() + ", '" + name + "')";
				else
					sql += ", (" + item.getId() + ", '" + name + "')";
			}// if/else
*/
		}// for
		/*try{
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			
		} catch (Exception e){
			
		} finally {
			db.endTransaction();
			db.execSQL("update status set is_ok = 1 where _id = 1;");
		}*/
		db.execSQL("update status set is_ok = 1 where _id = 1;");
		db.setTransactionSuccessful();
		db.endTransaction();
		
	}// addCityOrField
}
