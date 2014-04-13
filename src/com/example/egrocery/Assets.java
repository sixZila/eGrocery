package com.example.egrocery;

import java.util.ArrayList;
import java.util.HashMap;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.database.DBHandler;
import com.example.database.DBTables.ItemList;
import com.example.database.DBTables.MainList;
import android.content.ContentValues;
import android.content.Context;

public class Assets {
	private ArrayList<String> listView;
	private ArrayList<Item> itemView;
	private static DBHandler db;
	private static SQLiteDatabase database;
	private boolean gotList;
	private boolean gotItems;
	private HashMap<String, Long> listID;
	private String[] getDBList = {MainList._ID, MainList.LIST_NAME};
	private String[] getDBItems = {ItemList._ID, ItemList.ITEM_NAME, ItemList.CATEGORY, ItemList.SELECTED};
	
	public Assets(Context c) {
		if(db == null) {
			db = new DBHandler(c);
		}
		listView = new ArrayList<String>();
		itemView = new ArrayList<Item>();
		gotList = false;
		gotItems = false;
		listID = new HashMap<String, Long>();
	}
	
	//GET SHOPPING LIST
	public ArrayList<String> getlistView() {
		if(!gotList) {
			database = db.getReadableDatabase();
			Cursor c = database.query(MainList.TABLE_NAME, getDBList, null, null, null, null, null);
			
			if(c.moveToFirst()) {
				
				while (!c.isAfterLast()) {
					listID.put(c.getString(1), c.getLong(0));
					listView.add(c.getString(1));
					c.moveToNext();
				}
			}
			db.close();
			gotList = true;
		}
		return listView;
	}
	
	//GET ITEMS
	public ArrayList<Item> getItemView(String list) {
		if(!gotItems) {
			database = db.getReadableDatabase();
			Cursor c = database.query(ItemList.TABLE_NAME, getDBItems, ItemList.LIST + "= '" + list + "'", null, null, null, null);
			
			if(c.moveToFirst()) {
				while (!c.isAfterLast()) {
					itemView.add(new Item(c.getLong(0), c.getString(1), c.getString(2), c.getInt(3)>0));
					c.moveToNext();
				}
			}
			db.close();
			gotItems = true;
		}
		return itemView;
	}
	
	//UPDATE ITEM STATUS IN DB
	public void updateItem(Item i) {
		database = db.getWritableDatabase();

		// New value for one column
		ContentValues values = new ContentValues();
		values.put(ItemList.SELECTED, i.isSelected());

		// Which row to update, based on the ID
		String selection = ItemList._ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(i.id) };

		database.update(ItemList.TABLE_NAME, values, selection, selectionArgs);
	}
	
	//ADD AN ITEM TO DB
	public Item addItem(String name, String category, String list) {
		database = db.getWritableDatabase();
		long rowId;
		ContentValues values = new ContentValues();
		values.put(ItemList.ITEM_NAME, name);
		values.put(ItemList.CATEGORY, category);
		values.put(ItemList.LIST, list);
		values.put(ItemList.SELECTED, false);
		
		rowId = database.insert(ItemList.TABLE_NAME, null, values);
		Item i = new Item(rowId, name, category, false);
		itemView.add(i);
		db.close();
		
		return i;
	}
	
	//ADD A NEW SHOPPING LIST TO DB
	public void addList(String name) {
		database = db.getWritableDatabase();
		listView.add(name);
		ContentValues values = new ContentValues();
		values.put(MainList.LIST_NAME, name);
		
		long row = database.insert(MainList.TABLE_NAME, null, values);
		listID.put(name, row);
		db.close();
	}
	
	//DELETE LIST
	public void deleteList(String name) {
		database = db.getWritableDatabase();
		
		String selection = MainList._ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(listID.get(name)) };
		listView.remove(name);
		
		database.delete(MainList.TABLE_NAME, selection, selectionArgs);
		
		db.close();
	}
}
