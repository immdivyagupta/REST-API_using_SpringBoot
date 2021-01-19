package com.ebay.shopping.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ebay.shopping.valueobject.Item;

@Service
public class ShippingData {

	public static Set<Integer> approvedCategories = new HashSet<>();
	static Set<String> sellersEnrolled = new HashSet<>();
	static Map<String, Item> items = new HashMap<>();

	// Load with initial data
	static {
		// approved categories
		approvedCategories.add(1);
		approvedCategories.add(2);
		approvedCategories.add(3);

		sellersEnrolled.add("seller1");
		sellersEnrolled.add("seller2");
		sellersEnrolled.add("seller3");

		Item item1 = new Item("seller1", 1, 10.0);
		items.put("item1", item1);
		Item item2 = new Item("seller2", 2, -5.5);
		items.put("item2", item2);
		Item item3 = new Item("seller3", 3, 100.3);
		items.put("item3", item3);
	}
	
	public ShippingData() {
		
	}

	public Set<Integer> getApprovedCategories() {
		return approvedCategories;
	}

	public Set<String> getSellersEnrolled() {
		return sellersEnrolled;
	}

	public Map<String, Item> getItems() {
		return items;
	}
}
