package com.ebay.shopping.request;

public class ItemRequest {
	String item;
	String seller;
	int category;
	double minimumPrice;

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
}
