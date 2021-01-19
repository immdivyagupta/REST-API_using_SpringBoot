package com.ebay.shopping.response;

import java.util.Set;

public class CategoryResponse extends ShippingResponse {
	Set<Integer> categories;

	public Set<Integer> getCategories() {
		return categories;
	}

	public void setCategories(Set<Integer> categories) {
		this.categories = categories;
	}

}
