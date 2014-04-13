package com.example.database;

import android.provider.BaseColumns;

public final class DBTables {
	public DBTables() {
	}
	
	public static abstract class MainList implements BaseColumns {
		public static final String TABLE_NAME = "mainList";
		public static final String LIST_NAME = "name";
	}
	
	public static abstract class ItemList implements BaseColumns {
		public static final String TABLE_NAME = "itemList";
		public static final String ITEM_NAME = "name";
		public static final String CATEGORY = "category";
		public static final String LIST = "list";
		public static final String SELECTED = "selected";
	}
}
