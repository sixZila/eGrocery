package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.database.DBTables.ItemList;
import com.example.database.DBTables.MainList;

public class DBHandler extends SQLiteOpenHelper {
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_MAINLIST =
	    "CREATE TABLE " + MainList.TABLE_NAME + " (" +
	    MainList._ID + " INTEGER PRIMARY KEY" +
	    COMMA_SEP + MainList.LIST_NAME + TEXT_TYPE + " )";

	private static final String SQL_DELETE_MAINLIST =
	    "DROP TABLE IF EXISTS " + MainList.TABLE_NAME;
	
	private static final String SQL_CREATE_ITEMLIST =
		"CREATE TABLE " + ItemList.TABLE_NAME + " (" +
		ItemList._ID + " INTEGER PRIMARY KEY" +
		COMMA_SEP + ItemList.ITEM_NAME + TEXT_TYPE + 
		COMMA_SEP + ItemList.CATEGORY + TEXT_TYPE + 
		COMMA_SEP + ItemList.LIST + TEXT_TYPE +
		COMMA_SEP + ItemList.SELECTED + TEXT_TYPE + " )";

	private static final String SQL_DELETE_ITEMLIST =
		"DROP TABLE IF EXISTS " + ItemList.TABLE_NAME;
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Grocery.db";
	
    public DBHandler(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
    
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(SQL_CREATE_MAINLIST);
	    db.execSQL(SQL_CREATE_ITEMLIST);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	     db.execSQL(SQL_DELETE_MAINLIST);
	     db.execSQL(SQL_DELETE_ITEMLIST);
	     onCreate(db);
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    onUpgrade(db, oldVersion, newVersion);
	}
}
