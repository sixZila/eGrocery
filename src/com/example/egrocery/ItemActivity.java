package com.example.egrocery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class ItemActivity extends Activity{
	
	private Assets assets;
	private String list;
	private ExpandableListAdapter listAdapter;
	private ExpandableListView expListView;
	private List<String> listDataHeader;
	private List<String> categories;
	private HashMap<String, List<String>> listDataChild;
	private ArrayList<Assets.Item> items;
	private boolean[] hasCategory; 
	
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
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
	}
	
	private void prepareListData() {
		items = assets.getItemView(list);
		hasCategory = new boolean[20];
		categories = new ArrayList<String>();
		
		for(int i = 0; i < items.size(); i++) {
			if(!categories.contains(items.get(i).getCategory())) {
				categories.add(items.get(i).getCategory());
			}
		}
		
		listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        
        if(categories.contains("Canned Goods")) {
        	listDataHeader.add("Canned Goods");
        	List<String> cannedGoods = new ArrayList<String>();
        	
        	for(int i = 0; i < items.size(); i++) {
    			if(items.get(i).getCategory().equals("Canned Goods")) {
    				Log.d("Grocery", items.get(i).getName());
    				cannedGoods.add(items.get(i).getName());
    			}
    		}
        	
        	listDataChild.put("Canned Goods", cannedGoods);
        }
        if(categories.contains("Fruits and Vegetables")) {
        	listDataHeader.add("Fruits and Vegetables");
        	List<String> fruitsVeggies = new ArrayList<String>();
        	
        	for(int i = 0; i < items.size(); i++) {
    			if(items.get(i).getCategory().equals("Fruits and Vegetables")) {
    				fruitsVeggies.add(items.get(i).getName());
    			}
    		}
        	
        	listDataChild.put("Fruits and Vegetables", fruitsVeggies);
        }
        if(categories.contains("Meats")) {
        	listDataHeader.add("Meats");
        	List<String> meats = new ArrayList<String>();
        	
        	for(int i = 0; i < items.size(); i++) {
    			if(items.get(i).getCategory().equals("Meats")) {
    				meats.add(items.get(i).getName());
    			}
    		}

        	listDataChild.put("Meats", meats);
		}
        if(categories.contains("Seafood")) {
        	listDataHeader.add("Seafood");
        	List<String> seafood = new ArrayList<String>();
        	
        	for(int i = 0; i < items.size(); i++) {
    			if(items.get(i).getCategory().equals("Seafood")) {
    				seafood.add(items.get(i).getName());
    			}
    		}

        	listDataChild.put("Seafood", seafood);
		}
		if(categories.contains("Snacks")) {
			listDataHeader.add("Snacks");
        	List<String> snacks = new ArrayList<String>();
        	
        	for(int i = 0; i < items.size(); i++) {
    			if(items.get(i).getCategory().equals("Snack")) {
    				snacks.add(items.get(i).getName());
    			}
    		}

        	listDataChild.put("Snacks", snacks);
		}       
		if(categories.contains("Toiletries")) {
        	listDataHeader.add("Toiletries");
        	List<String> toiletries = new ArrayList<String>();
        	
        	for(int i = 0; i < items.size(); i++) {
    			if(items.get(i).getCategory().equals("Toiletries")) {
    				toiletries.add(items.get(i).getName());
    			}
    		}

        	listDataChild.put("Toiletries", toiletries);
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
        switch (item.getItemId()) {
            case R.id.addItemButton:
            	Intent addItemView = new Intent(getApplicationContext(), AddItemActivity.class);
            	addItemView.putExtra("list", list);
            	startActivityForResult(addItemView, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    	  if (requestCode == 1) {

    	     if(resultCode == RESULT_OK){      
    	         if(data.getStringExtra("category").equals("Canned Goods")) {
    	         	listDataChild.get("Canned Goods").add(data.getStringExtra("name"));
    	         } else if(data.getStringExtra("category").equals("Fruits and Vegetables")) {
    	        	 listDataChild.get("Fruits and Vegetables").add(data.getStringExtra("name"));
     	         } else if(data.getStringExtra("category").equals("Meats")) {
     	        	listDataChild.get("Meats").add(data.getStringExtra("name"));
      	         } else if(data.getStringExtra("category").equals("Seafood")) {
      	        	listDataChild.get("Seafood").add(data.getStringExtra("name"));
      	         } else if(data.getStringExtra("category").equals("Snacks")) {
      	        	listDataChild.get("Snacks").add(data.getStringExtra("name"));
      	         } else if(data.getStringExtra("category").equals("Toiletries")) {
      	        	listDataChild.get("Toiletries").add(data.getStringExtra("name"));
      	         }
    	         listAdapter.notifyDataSetChanged();
    	     }
    	     if (resultCode == RESULT_CANCELED) {    
    	         //Do nothing?
    	     }
    	  }
    	}
}
