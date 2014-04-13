package com.example.egrocery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	private EditText editText;
	private Button addButton;
	private ListView listView;
	private Assets assets;
	private ArrayAdapter<String> adapter;
	private Context context;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        
        //INITIALIZE UI VARIABLES
        editText = (EditText) findViewById(R.id.editText);
	    addButton = (Button) findViewById(R.id.addList);
	    listView = (ListView) findViewById(R.id.listView);
	    
	    //INITIALIZE LISTVIEW ITEMS
	    assets = new Assets(context);
	    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assets.getlistView());  
	    listView.setAdapter(adapter);

	    addButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		// TODO Auto-generated method stub
	    	   	assets.addList(editText.getText().toString());
	    	   	/*
	    	   	 * notifyDataSetChanged() notifies the attached observers that the underlying data has 
	    	   	 * been changed and any View reflecting the data set should refresh itself
	    	   	 */
	    	   	adapter.notifyDataSetChanged();
	    	}
	    });
	    listView.setOnItemClickListener(new OnItemClickListener() {
	        @Override  
	        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	        	Intent nextScreen = new Intent(context, ItemActivity.class);
	        	nextScreen.putExtra("title", assets.getlistView().get(position));
	        	startActivity(nextScreen);
	        }
	    });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_delete:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
}
