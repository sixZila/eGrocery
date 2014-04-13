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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText editText;
	private Button addButton;
	private ListView listView;
	private TextView emptyView;
	private Assets assets;
	private ArrayAdapter<String> adapter;
	private Context context;
	private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        
        context.deleteDatabase("Grocery.db");
        
        //INITIALIZE UI VARIABLES
        editText = (EditText) findViewById(R.id.editText);
	    addButton = (Button) findViewById(R.id.addList);
	    listView = (ListView) findViewById(R.id.listView);
	    emptyView = (TextView) findViewById(R.id.emptyMainList);
	    
	    //INITIALIZE LISTVIEW ITEMS
	    assets = new Assets(context);
	    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assets.getlistView());
	    listView.setEmptyView(emptyView);
	    listView.setAdapter(adapter);

	    addButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		// TODO Auto-generated method stub
	    		if(editText.getText().toString().equals("")) {
	    			toast = Toast.makeText(context, "Error: Please input a list name.", Toast.LENGTH_LONG);
	    			toast.show();
	    		} else if(assets.getlistView().contains(editText.getText().toString())) {
	    			toast = Toast.makeText(context, "Error: Duplicate list names.", Toast.LENGTH_LONG);
	    			toast.show();
	    		} else {
		    	   	assets.addList(editText.getText().toString());
		    	   	adapter.notifyDataSetChanged();
	    		}
	    		editText.setText("");
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
