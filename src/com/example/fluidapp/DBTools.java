package com.example.fluidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTools extends SQLiteOpenHelper{

	public DBTools(Context context) {
		super(context, "Fluid.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String query = "CREATE TABLE userSettings (id INTEGER, isMan Bool, weight INTEGER, fever INTEGER, extraWater INTEGER)";
		db.execSQL(query);
		
		String query2 = "CREATE TABLE userMessages (id INTEGER, msgTime TIMESTAMP, msgText TEXT)";
		db.execSQL(query2);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query = "DROP TABLE IF EXISTS userSettings";
		db.execSQL(query);
		
		String query2 = "DROP TABLE IF EXISTS userMessages";
		db.execSQL(query2);
		
		onCreate(db);
	}
	
	public void insertUser(HashMap<String, String>queryValues){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("isMan", queryValues.get("isMan"));
		values.put("weight", queryValues.get("weight"));
		values.put("fever", queryValues.get("fever"));
		values.put("extraWater", queryValues.get("extraWater"));
		db.insert("userSettings", null, values);
		db.close();
	}
	public void insertMessage(HashMap<String, String>queryValues){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("msgTime", queryValues.get("msgTime"));
		values.put("msgText", queryValues.get("msgText"));
		db.insert("userMessages", null, values);
		db.close();
	}
	
	public int updateUser(HashMap<String, String>queryValues){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("isMan", queryValues.get("isMan"));
		values.put("weight", queryValues.get("weight"));
		//values.put("fever", queryValues.get("fever"));
		values.put("extraWater", queryValues.get("extraWater"));
		System.out.println(queryValues.get("id") + "-----------------------");
		
		return db.update("userSettings", values, null, null);
		
		//return db.update("userSettings", values, "id" + " = ?", new String[] { queryValues.get("id") });
	}
	public int updateUserFever(HashMap<String, String>queryValues){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("fever", queryValues.get("fever"));
		
		return db.update("userSettings", values, null, null);
	}
	public int updateMessage(HashMap<String, String>queryValues){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("msgTime", queryValues.get("msgTime"));
		values.put("msgText", queryValues.get("msgText"));
		
		//return db.update("userMessages", values, "id" + " = ?", new String[] {queryValues.get("id")});
		return db.update("userMessages", values, null, null);
	}
	
	public void deleteMessage(String id){
		SQLiteDatabase database = getWritableDatabase();
		String query = "DELETE FROM userMessages WHERE id LIKE %";
		
		database.execSQL(query);
		
	}
	public ArrayList<HashMap<String, String>> getUserSettings(){
		ArrayList<HashMap<String, String>> userSettings = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM userSettings";
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()){
			do{
				HashMap<String, String> hashMap = new HashMap<String, String>();
				hashMap.put("id", cursor.getString(0));
				hashMap.put("isMan", cursor.getString(1));
				hashMap.put("weight", cursor.getString(2));
				hashMap.put("fever", cursor.getString(3));
				hashMap.put("extraWater", cursor.getString(4));
				
				userSettings.add(hashMap);
			}while(cursor.moveToNext());
		}
		
		
		return userSettings;
	}
	
	public ArrayList<HashMap<String, String>> getUserMessages(){
		ArrayList<HashMap<String, String>> userMessages = new ArrayList<HashMap<String,String>>();
		String query = "SELECT * FROM userSettings";
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()){
			do{
				HashMap<String, String> hashMap = new HashMap<String, String>();
				hashMap.put("id", cursor.getString(0));
				hashMap.put("msgTime", cursor.getString(1));
				hashMap.put("msgText", cursor.getString(2));
				
				userMessages.add(hashMap);
			}while(cursor.moveToNext());
		}
		
		
		return userMessages;
	}
	
}
