package com.ebay.shopping.valueobject;

public class Item {
	String seller;
	int category;
	double minimumPrice;

	public Item(String seller, int category, double minimumPrice) {
		this.seller = seller;
		this.category = category;
		this.minimumPrice = minimumPrice;
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

	public void setMinmumPrice(double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
}
