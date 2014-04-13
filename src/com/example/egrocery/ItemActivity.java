package com.example.egrocery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class ItemActivity extends Activity{
	
	private Assets assets;
	private String list;
	private ExpandableListAdapter listAdapter;
	private ExpandableListView expListView;
	private List<String> listDataHeader;
	private HashMap<String, List<Item>> listDataChild;
	private HashMap<String, List<Item>> listDataChildChecked;
	private HashMap<String, List<Item>> listDataChildUnchecked;
	private ArrayList<Item> items;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_ui);
        
        //INITIALIZE UI VARIABLES
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        Intent i = getIntent();
        list = i.getStringExtra("title");
        assets = new Assets(getApplicationContext());
        setTitle(list);
        
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, assets);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
	}
	
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
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
    	List<Item> temp;
    	List<Item> temp2;
        switch (item.getItemId()) {
            case R.id.addItemButton:
            	Intent addItemView = new Intent(getApplicationContext(), AddItemActivity.class);
            	addItemView.putExtra("list", list);
            	startActivityForResult(addItemView, 1);
                return true;
            case R.id.showAllButton:
            	listAdapter.setListDataChild(listDataChild);
            	listAdapter.notifyDataSetChanged();
            	return true;
            case R.id.showCheckedButton:
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
            	listAdapter.setListDataChild(listDataChildChecked);
            	listAdapter.notifyDataSetChanged();
            	return true;
            case R.id.showUncheckedButton:
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
            	listAdapter.setListDataChild(listDataChildUnchecked);
            	listAdapter.notifyDataSetChanged();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    	  if (requestCode == 1) {

    	     if(resultCode == RESULT_OK){
    	    	 String category, name;
    	    	 name = data.getStringExtra("name");
    	    	 category = data.getStringExtra("category");
    	    	 Item item = assets.addItem(name, category, list);
    	    	 
    	         if(listDataChild.get(category) == null) {
    	        	 listDataChild.put(category, new ArrayList<Item>());
    	        	 listDataHeader.add(category);
    	         }
    	         
    	         Collections.sort(listDataHeader);
    	        	
    	         listDataChild.get(category).add(item);
    	         	
    	         listAdapter.notifyDataSetChanged();
    	     }
    	     if (resultCode == RESULT_CANCELED) {
    	         //Do nothing?
    	     }
    	  }
    	}
}
