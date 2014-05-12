package com.example.egrocery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ItemActivity extends Activity{

	private Assets assets;
	private String list;
	private TextView emptyView;
	private ExpandableListAdapter listAdapter;
	private ExpandableListView expListView;
	private Button showAllButton;
	private Button showCheckedButton;
	private Button showUncheckedButton;
	private List<String> listDataHeader;
	private HashMap<String, List<Item>> listDataChild;
	private HashMap<String, List<Item>> listDataChildChecked;
	private HashMap<String, List<Item>> listDataChildUnchecked;
	private ArrayList<Item> items;
	private ArrayList<String> uncheckedCategories;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_ui);

		//INITIALIZE UI VARIABLES
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		emptyView = (TextView) findViewById(R.id.emptyItemList);
		showAllButton = (Button) findViewById(R.id.showAllItemButton);
		showCheckedButton = (Button) findViewById(R.id.showCheckedItemButton);
		showUncheckedButton = (Button) findViewById(R.id.showUncheckedItemButton);

		Intent i = getIntent();
		list = i.getStringExtra("title");
		assets = new Assets(getApplicationContext());
		setTitle(list);
		
		uncheckedCategories = new ArrayList<String>();

		// preparing list data
		prepareListData();
		//IMPLEMENT LIST
		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, assets, this);

		//SET LIST
		expListView.setEmptyView(emptyView);
		expListView.setAdapter(listAdapter);
		showAllButton.setBackgroundColor(Color.GRAY);
		showCheckedButton.setBackgroundColor(Color.WHITE);
		showUncheckedButton.setBackgroundColor(Color.WHITE);

		//ACTION LISTENERS
		showAllButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				listAdapter.setListDataChild(listDataChild);
				listAdapter.notifyDataSetChanged();
				listAdapter.setStatus(0);
				showAllButton.setBackgroundColor(Color.GRAY);
				showCheckedButton.setBackgroundColor(Color.WHITE);
				showUncheckedButton.setBackgroundColor(Color.WHITE);
			}
		});
		showCheckedButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				prepareCheckedData();
				listAdapter.setListDataChild(listDataChildChecked);
				listAdapter.notifyDataSetChanged();
				listAdapter.setStatus(2);
				showAllButton.setBackgroundColor(Color.WHITE);
				showCheckedButton.setBackgroundColor(Color.GRAY);
				showUncheckedButton.setBackgroundColor(Color.WHITE);
			}
		});
		showUncheckedButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				prepareUncheckedData();
				listAdapter.setListDataChild(listDataChildUnchecked);
				listAdapter.notifyDataSetChanged();
				listAdapter.setStatus(1);
				showAllButton.setBackgroundColor(Color.WHITE);
				showCheckedButton.setBackgroundColor(Color.WHITE);
				showUncheckedButton.setBackgroundColor(Color.GRAY);
			}
		});
	}

	public void prepareUncheckedData() {
		List<Item> temp;
		List<Item> temp2;
		for(int i = 0; i < listDataHeader.size(); i++) {
			temp = listDataChild.get(listDataHeader.get(i));
			if(listDataChildUnchecked.get(listDataHeader.get(i)) != null) {
				temp2 = listDataChildUnchecked.get(listDataHeader.get(i));

				if(!temp2.isEmpty()) {
					temp2.clear();
				}
			} else {
				temp2 = new ArrayList<Item>();
				listDataChildUnchecked.put(listDataHeader.get(i), temp2);
			}
			for(int j = 0; j < temp.size(); j++) {
				if(!temp.get(j).isSelected()) {
					temp2.add(temp.get(j));
				}
			}
		}
	}

	public void prepareCheckedData() {
		List<Item> temp;
		List<Item> temp2;
		for(int i = 0; i < listDataHeader.size(); i++) {
			temp = listDataChild.get(listDataHeader.get(i));
			if(listDataChildChecked.get(listDataHeader.get(i)) != null) {
				temp2 = listDataChildChecked.get(listDataHeader.get(i));

				if(!temp2.isEmpty()) {
					temp2.clear();
				}
			} else {
				temp2 = new ArrayList<Item>();
				listDataChildChecked.put(listDataHeader.get(i), temp2);
			}
			for(int j = 0; j < temp.size(); j++) {
				if(temp.get(j).isSelected()) {
					temp2.add(temp.get(j));
				}
			}
		}
	}

	//PREPARE THE SHOPPING LIST
	private void prepareListData() {
		if(items == null) {
			items = assets.getItemView(list);
		}
		listDataHeader = new ArrayList<String>();

		for(int i = 0; i < items.size(); i++) {
			if(!listDataHeader.contains(items.get(i).getCategory())) {
				listDataHeader.add(items.get(i).getCategory());
			}
		}

		Collections.sort(listDataHeader);

		listDataChild = new HashMap<String, List<Item>>();
		listDataChildChecked = new HashMap<String, List<Item>>();
		listDataChildUnchecked = new HashMap<String, List<Item>>();

		for(int i = 0; i < listDataHeader.size(); i++) {
			List<Item> itemList = new ArrayList<Item>();

			for(int j = 0; j < items.size(); j++) {
				if(items.get(j).getCategory().equals(listDataHeader.get(i))) {
					itemList.add(items.get(j));
				}
			}

			listDataChild.put(listDataHeader.get(i), itemList);
		}
	}

	//FOR CONTEXT OPTIONS
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_list_menu, menu);
		return true;
	}

	//MENU ACTIONS
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection

		switch (item.getItemId()) {
		//ADD AN ITEM TO THE LIST
		case R.id.addItemButton:
			Intent addItemView = new Intent(getApplicationContext(), AddItemActivity.class);
			addItemView.putExtra("list", list);
			startActivityForResult(addItemView, 1);
			return true;
		case R.id.groceryView:
			Intent groceryView = new Intent(getApplicationContext(), GroceryLayoutActivity.class);
			populateUncheckedHeader();
			groceryView.putExtra("categories", uncheckedCategories);
			startActivity(groceryView);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	//GET UNCHECKED CATEGORIES FOR GROCERY VIEW
	public void populateUncheckedHeader() {
		prepareUncheckedData();
		uncheckedCategories.clear();
		
		for(String s: listDataHeader) {
			if(!listDataChildUnchecked.get(s).isEmpty()) {
				uncheckedCategories.add(s);
			}
		}
	}
	
	//UPDATE LIST AFTER ADDING A NEW ITEM
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			//ADDS ITEM TO LIST
			if(resultCode == RESULT_OK){
				String category, name;
				name = data.getStringExtra("name");
				category = data.getStringExtra("category");
				Item item = assets.addItem(name, category, list);

				if(listDataChild.get(category) == null) {
					listDataChild.put(category, new ArrayList<Item>());
					listDataHeader.add(category);
					Collections.sort(listDataHeader);
				}

				listDataChild.get(category).add(item);

				if(listAdapter.getStatus() == 1) {
					prepareUncheckedData();
				} else if(listAdapter.getStatus() == 2) {
					prepareCheckedData();
				}

				listAdapter.notifyDataSetChanged();
			}
			if (resultCode == RESULT_CANCELED) {
				//Do nothing?
			}
		}
	}
}
