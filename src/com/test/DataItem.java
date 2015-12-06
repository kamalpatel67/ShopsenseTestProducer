package com.test;

import java.util.List;

public class DataItem {

	private String item_name;
	private String shade_name;
	private int mrp;
	private List<Size_Quantity> size_quantity;
	
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getShade_name() {
		return shade_name;
	}
	public void setShade_name(String shade_name) {
		this.shade_name = shade_name;
	}
	public int getMrp() {
		return mrp;
	}
	public void setMrp(int mrp) {
		this.mrp = mrp;
	}
	public List<Size_Quantity> getSize_quantity() {
		return size_quantity;
	}
	public void setSize_quantity(List<Size_Quantity> size_quantity) {
		this.size_quantity = size_quantity;
	}
}
