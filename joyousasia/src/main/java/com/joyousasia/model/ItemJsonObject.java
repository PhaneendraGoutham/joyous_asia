package com.joyousasia.model;


public class ItemJsonObject{
	
	private Integer itemId;
	private String itemName;
	private double itemPrice;
	private int itemQuantity;
	
	/* Constructor (You can create an constructor for default value setting) */
	public ItemJsonObject(){
		
	}
	
	
	/* toString() Function */
	@Override
	public String toString() {
		return "ItemJsonObject ["
				+ "item_id=" + itemId + ", "
				+ "item_name=" + itemName + ", "
				+ "item_price=" + itemPrice + ", "
				+ "item_quantity=" + itemQuantity
				+"]";
	}
	
	
	/* Getter and Setter */
	public Integer getItemId() {
		return itemId;
	}


	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	

	public double getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}


	public int getItemQuantity() {
		return itemQuantity;
	}


	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	
}
