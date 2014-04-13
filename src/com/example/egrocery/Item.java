package com.example.egrocery;

public class Item {
	String name;
	String category;
	boolean selected;
	long id;
	
	public Item(String name, String category, boolean selected) {
		this.name = name;
		this.category = category;
		this.selected = selected;
	}
	
	public Item(long id, String name, String category, boolean selected) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.selected = selected;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
