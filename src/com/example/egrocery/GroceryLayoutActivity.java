package com.example.egrocery;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroceryLayoutActivity extends Activity{

	private ArrayList<String> categories;
	private Button exitButton;
	private Button aisle1;
	private Button aisle2;
	private Button aisle3;
	private Button aisle4;
	private Button aisle5;
	private Button aisle6;
	private Button aisle7;
	private Button aisle8;
	private Button aisle9;
	private Button aisle10;
	private Button aisle11;
	private Button aisle12;
	private Button aisle14;
	private Button aisle15;
	private Button aisle16;
	private Button aisle17;
	private Button aisle18;
	private Button aisle19;
	private Button aisle20;
	private Button aisle21;
	private Button aisle22;
	private Button aisle23;
	private Button aisle24;
	private Button breakfastAisle;
	private Button babyAisle;
	private Button milkAisle;
	private Button dairyAisle;
	private Button meatAisle;
	private Button seafoodAisle;
	private Button veggieAisle;
	private Button fruitAisle;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grocery_layout);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//INSTANTIATE BUTTONS
		aisle1 = (Button) findViewById(R.id.aisle1);
		aisle2 = (Button) findViewById(R.id.aisle2);
		aisle3 = (Button) findViewById(R.id.aisle3);
		aisle4 = (Button) findViewById(R.id.aisle4);
		aisle5 = (Button) findViewById(R.id.aisle5);
		aisle6 = (Button) findViewById(R.id.aisle6);
		aisle7 = (Button) findViewById(R.id.aisle7);
		aisle8 = (Button) findViewById(R.id.aisle8);
		aisle9 = (Button) findViewById(R.id.aisle9);
		aisle10 = (Button) findViewById(R.id.aisle10);
		aisle11 = (Button) findViewById(R.id.aisle11);
		aisle12 = (Button) findViewById(R.id.aisle12);
		aisle14 = (Button) findViewById(R.id.aisle14);
		aisle15 = (Button) findViewById(R.id.aisle15);
		aisle16 = (Button) findViewById(R.id.aisle16);
		aisle17 = (Button) findViewById(R.id.aisle17);
		aisle18 = (Button) findViewById(R.id.aisle18);
		aisle19 = (Button) findViewById(R.id.aisle19);
		aisle20 = (Button) findViewById(R.id.aisle20);
		aisle21 = (Button) findViewById(R.id.aisle21);
		aisle22 = (Button) findViewById(R.id.aisle22);
		aisle23 = (Button) findViewById(R.id.aisle23);
		aisle24 = (Button) findViewById(R.id.aisle24);
		breakfastAisle = (Button) findViewById(R.id.breakfastAisle);
		babyAisle = (Button) findViewById(R.id.babyAisle);
		milkAisle = (Button) findViewById(R.id.milkAisle);
		dairyAisle = (Button) findViewById(R.id.dairyAisle);
		meatAisle = (Button) findViewById(R.id.meatAisle);
		seafoodAisle = (Button) findViewById(R.id.seafoodAisle);
		veggieAisle = (Button) findViewById(R.id.veggieAisle);
		fruitAisle = (Button) findViewById(R.id.fruitAisle);
		exitButton = (Button) findViewById (R.id.exitGroceryViewButton);
		
		Intent i = getIntent();
		categories = i.getStringArrayListExtra("categories");
		
		for(String s : categories) {
			highlight(s);
		}
		
		getFirstCategory();

		exitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	public void getFirstCategory() {
		if(categories.contains("Rice") || categories.contains("Sugar")) {
			aisle1.setBackgroundColor(Color.RED);
		}else if(categories.contains("Fruits")) {
			fruitAisle.setBackgroundColor(Color.RED);
		}else if(categories.contains("Canned Goods")) {
			aisle2.setBackgroundColor(Color.RED);
		}else if(categories.contains("Vegetables")) {
			veggieAisle.setBackgroundColor(Color.RED);
		}else if(categories.contains("Cooking Oils")) {
			aisle3.setBackgroundColor(Color.RED);
		}else if(categories.contains("Noodles")) {
			aisle4.setBackgroundColor(Color.RED);
		}else if(categories.contains("Seafood")) {
			seafoodAisle.setBackgroundColor(Color.RED);
		}else if(categories.contains("Kitchenware")) {
			aisle5.setBackgroundColor(Color.RED);
		}else if(categories.contains("Sauces")) {
			aisle6.setBackgroundColor(Color.RED);
		}else if(categories.contains("Picnic Items")) {
			aisle7.setBackgroundColor(Color.RED);
		}else if(categories.contains("Food Storage")) {
			aisle8.setBackgroundColor(Color.RED);
		}else if(categories.contains("Meats")) {
			meatAisle.setBackgroundColor(Color.RED);
		}else if(categories.contains("Shampoo and Conditioner")) {
			aisle9.setBackgroundColor(Color.RED);
			aisle10.setBackgroundColor(Color.RED);
		}else if(categories.contains("Hair Essentials")) {
			aisle11.setBackgroundColor(Color.RED);
		}else if(categories.contains("Body Essentials")) {
			aisle12.setBackgroundColor(Color.RED);
		}else if(categories.contains("Sanitary Needs")) {
			aisle14.setBackgroundColor(Color.RED);
		}else if(categories.contains("Kitchen Liquids")) {
			aisle15.setBackgroundColor(Color.RED);
		}else if(categories.contains("Laundry")) {
			aisle16.setBackgroundColor(Color.RED);
		}else if(categories.contains("Pet Food")) {
			aisle17.setBackgroundColor(Color.RED);
		}else if(categories.contains("Pillows and Doormats")) {
			aisle18.setBackgroundColor(Color.RED);
		}else if(categories.contains("Dairy")) {
			dairyAisle.setBackgroundColor(Color.RED);
		}else if(categories.contains("Drinks")) {
			aisle19.setBackgroundColor(Color.RED);
			aisle20.setBackgroundColor(Color.RED);
		}else if(categories.contains("Chips")) {
			aisle21.setBackgroundColor(Color.RED);
		}else if(categories.contains("Sweets")) {
			aisle22.setBackgroundColor(Color.RED);
			aisle23.setBackgroundColor(Color.RED);
		}else if(categories.contains("Asian Imports")) {
			aisle23.setBackgroundColor(Color.RED);
		}else if(categories.contains("Western Imports") || categories.contains("Baked Goods")) {
			aisle24.setBackgroundColor(Color.RED);
		}else if(categories.contains("Milk")) {
			milkAisle.setBackgroundColor(Color.RED);
		}else if(categories.contains("Breakfast")) {
			breakfastAisle.setBackgroundColor(Color.RED);
		}else if(categories.contains("Baby Needs")) {
			babyAisle.setBackgroundColor(Color.RED);
		}
	}
	
	public void highlight(String s) {
		if(s.equals("Rice") || s.equals("Sugar")) {
			aisle1.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Fruits")) {
			fruitAisle.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Canned Goods")) {
			aisle2.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Vegetables")) {
			veggieAisle.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Cooking Oils")) {
			aisle3.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Noodles")) {
			aisle4.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Seafood")) {
			seafoodAisle.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Kitchenware")) {
			aisle5.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Sauces")) {
			aisle6.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Picnic Items")) {
			aisle7.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Food Storage")) {
			aisle8.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Meats")) {
			meatAisle.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Shampoo and Conditioner")) {
			aisle9.setBackgroundColor(Color.BLUE);
			aisle10.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Hair Essentials")) {
			aisle11.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Body Essentials")) {
			aisle12.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Sanitary Needs")) {
			aisle14.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Kitchen Liquids")) {
			aisle15.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Laundry")) {
			aisle16.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Pet Food")) {
			aisle17.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Pillows and Doormats")) {
			aisle18.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Dairy")) {
			dairyAisle.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Drinks")) {
			aisle19.setBackgroundColor(Color.BLUE);
			aisle20.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Chips")) {
			aisle21.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Sweets")) {
			aisle22.setBackgroundColor(Color.BLUE);
			aisle23.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Asian Imports")) {
			aisle23.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Western Imports") || categories.contains("Baked Goods")) {
			aisle24.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Baby Needs")) {
			babyAisle.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Breakfast")) {
			breakfastAisle.setBackgroundColor(Color.BLUE);
		}else if(s.equals("Milk")) {
			milkAisle.setBackgroundColor(Color.BLUE);
		}
	}
}
