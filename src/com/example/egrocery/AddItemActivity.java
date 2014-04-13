package com.example.egrocery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddItemActivity extends Activity{
	
	private Button addButton;
	private Button cancelButton;
	private EditText itemNameField;
	private Spinner category;
	private String list;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        Intent i = getIntent();
        list = i.getStringExtra("list");
        setTitle("Add Item to " + list);
        category = (Spinner) findViewById(R.id.categoryChooser);
        itemNameField = (EditText) findViewById(R.id.itemNameField);
        addButton = (Button) this.findViewById(R.id.addItem);
        cancelButton = (Button) this.findViewById(R.id.cancelAddItem);
        
        //ADD CATEGORY LIST TO CATEGORIES
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        
        
        //ACTION LISTENERES
        addButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		Intent returnIntent = new Intent();
	    		returnIntent.putExtra("name", itemNameField.getText().toString());
	    		returnIntent.putExtra("category", category.getSelectedItem().toString());
	    		setResult(RESULT_OK,returnIntent);
	    		finish();
	    	}
	    });
        cancelButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		finish();
	    	}
	    });
	}
}
