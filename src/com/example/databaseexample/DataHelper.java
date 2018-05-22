package com.example.databaseexample;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path.FillType;

public class DataHelper {

	SQLiteDatabase sqlitedatabase;
	Context context;
	SQLiteHelper sqlitehelper;
	
	String databasename = "testDatabase";
	public String TableName = "company";
	int database_version = 2;
	
	public ArrayList<dataclass> arraylistdata = new ArrayList<>();
	
	public DataHelper(Context c)
	{
		context = c;
	}
	
	public DataHelper openToRead() throws SQLiteException
	{
		sqlitehelper = new SQLiteHelper(context, databasename, null, database_version);
		sqlitedatabase = sqlitehelper.getReadableDatabase();
		return this;
		
	}
	public DataHelper openToWrite() throws SQLiteException
	{
		sqlitehelper = new SQLiteHelper(context, databasename, null, database_version);
		sqlitedatabase = sqlitehelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		sqlitedatabase.close();
	}

	public long insert(String name)
	{
		ContentValues contentvalues = new ContentValues();
	//	contentvalues.put("id", id);
		contentvalues.put("name", name);
		return sqlitedatabase.insert(TableName, null, contentvalues);
		
	}
	
	public ArrayList<dataclass> retrivedata()
	{
		
		arraylistdata.clear();
		Cursor cursor = sqlitedatabase.rawQuery("select * from company;", null);
		try
		{
			if(cursor.moveToFirst())
			{
				do{
					dataclass data = new dataclass();
					
				//	data.setId(cursor.getString(1));
					data.setName(cursor.getString(1));
					arraylistdata.add(data);
				}
				while(cursor.moveToNext());
			}
			cursor.close();
			return arraylistdata;
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			cursor.close();
		}
		return arraylistdata;
		
	}
	
public class SQLiteHelper extends SQLiteOpenHelper
{

	public SQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table "+ TableName + "(id INTEGER PRIMARY KEY,name text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TableName);
		onCreate(db);
	}
}
}