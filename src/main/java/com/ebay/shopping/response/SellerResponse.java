package com.ebay.shopping.response;

import java.util.Set;

public class SellerResponse extends ShippingResponse {
	Set<String> sellers;

	public Set<String> getSellers() {
		return sellers;
	}

	public void setSellers(Set<String> sellers) {
		this.sellers = sellers;
	}

}
