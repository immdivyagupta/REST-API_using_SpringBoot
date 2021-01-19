package com.ebay.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.shopping.request.CategoryRequest;
import com.ebay.shopping.request.ItemRequest;
import com.ebay.shopping.request.SellerRequest;
import com.ebay.shopping.response.CategoryResponse;
import com.ebay.shopping.response.ItemResponse;
import com.ebay.shopping.response.SellerResponse;
import com.ebay.shopping.response.ShippingResponse;
import com.ebay.shopping.service.ShippingService;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

	@Autowired
	private ShippingService service;

	/**
	 * Returns list of approved categories
	 * 
	 * @return CategoryResponse
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/category")
	public CategoryResponse getCategories() {
		return service.getCategories();
	}

	/**
	 * Add an item into pre-approved category
	 * 
	 * @param categoryRequest
	 * @return CategoryResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/category")
	public CategoryResponse addCategory(@RequestBody CategoryRequest categoryRequest) {
		return service.addCategory(categoryRequest);
	}

	/**
	 * Remove an entry from pre-approved category
	 * 
	 * @param category
	 * @return CategoryResponse
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/category")
	public CategoryResponse deleteCategory(@RequestParam int category) {
		return service.deleteCategory(category);
	}

	/**
	 * Returns the approved sellers list
	 * 
	 * @return SellerResponse
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/seller")
	public SellerResponse getSellers() {
		return service.getSellers();
	}

	/**
	 * Add a seller into approved list
	 * 
	 * @param sellerRequest
	 * @return SellerResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/seller")
	public SellerResponse addSeller(@RequestBody SellerRequest sellerRequest) {
		return service.addSeller(sellerRequest);
	}

	/**
	 * Remove a seller from approved list
	 * 
	 * @param seller
	 * @return SellerResponse
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/seller")
	public SellerResponse deleteSeller(@RequestParam String seller) {
		return service.deleteSeller(seller);
	}

	/**
	 * Returns the list of available items
	 * 
	 * @return ItemResponse
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/item")
	public ItemResponse getItems() {
		return service.getItems();
	}

	/**
	 * Add an item into available items list
	 * 
	 * @param itemRequest
	 * @return ItemResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/item")
	public ItemResponse addItem(@RequestBody ItemRequest itemRequest) {
		return service.addItem(itemRequest);
	}

	/**
	 * Update an item into available items list
	 * 
	 * @param itemRequest
	 * @return ItemResponse
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/item")
	public ItemResponse updateItem(@RequestBody ItemRequest itemRequest) {
		return service.updateItem(itemRequest);
	}

	/**
	 * Remove an item from available items list
	 * 
	 * @param title
	 * @return ItemResponse
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/item")
	public ItemResponse deleteItem(@RequestParam String title) {
		return service.deleteItem(title);
	}

	/**
	 * Check eligibility of item
	 * 
	 * @param title
	 * @param seller
	 * @param category
	 * @param price
	 * @return response message
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/eligible")
	public ShippingResponse checkEligibility(@RequestParam String title, @RequestParam String seller,
			@RequestParam int category, @RequestParam double price) {
		return service.checkEligibility(title, seller, category, price);
	}
}
