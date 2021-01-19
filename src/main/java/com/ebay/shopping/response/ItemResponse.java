package com.ebay.shopping.response;

import java.util.Map;

import com.ebay.shopping.valueobject.Item;

public class ItemResponse extends ShippingResponse {
	Map<String, Item> items;

	public Map<String, Item> getItems() {
		return items;
	}

	public void setItems(Map<String, Item> items) {
		this.items = items;
	}

}
