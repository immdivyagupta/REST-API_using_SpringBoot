package com.ebay.shopping.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebay.shopping.data.ShippingData;
import com.ebay.shopping.request.CategoryRequest;
import com.ebay.shopping.request.ItemRequest;
import com.ebay.shopping.request.SellerRequest;
import com.ebay.shopping.response.CategoryResponse;
import com.ebay.shopping.response.ItemResponse;
import com.ebay.shopping.response.SellerResponse;
import com.ebay.shopping.response.ShippingResponse;
import com.ebay.shopping.util.ShippingMessages;
import com.ebay.shopping.valueobject.Item;

@Service
public class ShippingService {

	private static final Logger logger = LoggerFactory.getLogger(ShippingService.class);

	private static final String EMPTY = "";

	@Autowired
	private ShippingData shippingData;

	/**
	 * Returns list of approved categories
	 * 
	 * @return CategoryResponse
	 */
	public CategoryResponse getCategories() {
		logger.info("Invoke ShippingService.getCategories() method");
		CategoryResponse response = new CategoryResponse();
		response.setCategories(shippingData.getApprovedCategories());
		return response;
	}

	/**
	 * Add an item into pre-approved category
	 * 
	 * @param categoryRequest
	 * @return CategoryResponse
	 */
	public CategoryResponse addCategory(CategoryRequest categoryRequest) {
		logger.info("Invoke ShippingService.addCategory() method");
		CategoryResponse response = new CategoryResponse();
		if (!shippingData.getApprovedCategories().contains(categoryRequest.getCategory())) {
			shippingData.getApprovedCategories().add(categoryRequest.getCategory());
			response.setMessage(ShippingMessages.CATEGORY_ADDED);
			logger.info("ShippingService.addCategory() method - category added");
		} else {
			response.setMessage(ShippingMessages.CATEGORY_AVAILABLE);
			logger.error("ShippingService.getCategories() method - category available");
		}
		return response;
	}

	/**
	 * Remove an entry from pre-approved category
	 * 
	 * @param category
	 * @return CategoryResponse
	 */
	public CategoryResponse deleteCategory(int category) {
		logger.info("Invoke ShippingService.deleteCategory() method");
		CategoryResponse response = new CategoryResponse();
		if (shippingData.getApprovedCategories().contains(category)) {
			shippingData.getApprovedCategories().remove(category);
			response.setMessage(ShippingMessages.CATEGORY_REMOVED);
			logger.info("ShippingService.deleteCategory() method - category removed");
		} else {
			response.setMessage(ShippingMessages.CATEGORY_NOT_AVAILABLE);
			logger.error("ShippingService.deleteCategory() method - category not available");
		}
		return response;
	}

	/**
	 * Returns the approved sellers list
	 * 
	 * @return SellerResponse
	 */
	public SellerResponse getSellers() {
		logger.info("Invoke ShippingService.getSellers() method");
		SellerResponse response = new SellerResponse();
		response.setSellers(shippingData.getSellersEnrolled());
		return response;
	}

	/**
	 * Add a seller into approved list
	 * 
	 * @param sellerRequest
	 * @return SellerResponse
	 */
	public SellerResponse addSeller(SellerRequest sellerRequest) {
		logger.info("Invoke ShippingService.addSeller() method");
		SellerResponse response = new SellerResponse();
		if (!shippingData.getSellersEnrolled().contains(sellerRequest.getSeller())) {
			shippingData.getSellersEnrolled().add(sellerRequest.getSeller());
			response.setMessage(ShippingMessages.SELLER_ADDED);
			logger.info("ShippingService.addSeller() method - seller added");
		} else {
			response.setMessage(ShippingMessages.SELLER_AVAILABLE);
			logger.error("ShippingService.addSeller() method - seller available");
		}
		return response;
	}

	/**
	 * Remove a seller from approved list
	 * 
	 * @param seller
	 * @return SellerResponse
	 */
	public SellerResponse deleteSeller(String seller) {
		logger.info("Invoke ShippingService.deleteSeller() method");
		SellerResponse response = new SellerResponse();
		if (shippingData.getSellersEnrolled().contains(seller)) {
			shippingData.getSellersEnrolled().remove(seller);
			response.setMessage(ShippingMessages.SELLER_REMOVED);
			logger.info("ShippingService.deleteSeller() method - seller rmeoved");
		} else {
			response.setMessage(ShippingMessages.SELLER_NOT_AVAILABLE);
			logger.error("ShippingService.deleteSeller() method - seller not available");
		}
		return response;
	}

	/**
	 * Returns the list of available items
	 * 
	 * @return ItemResponse
	 */
	public ItemResponse getItems() {
		logger.info("Invoke ShippingService.getItems() method");
		ItemResponse response = new ItemResponse();
		response.setItems(shippingData.getItems());
		return response;
	}

	/**
	 * Add an item into available items list
	 * 
	 * @param itemRequest
	 * @return ItemResponse
	 */
	public ItemResponse addItem(ItemRequest itemRequest) {
		logger.info("Invoke ShippingService.addItem() method");
		ItemResponse response = new ItemResponse();
		if (!shippingData.getItems().containsKey(itemRequest.getItem())) {
			Item item = new Item(itemRequest.getSeller(), itemRequest.getCategory(), itemRequest.getMinimumPrice());
			shippingData.getItems().put(itemRequest.getItem(), item);
			response.setMessage(ShippingMessages.ITEM_ADDED);
			logger.info("ShippingService.addItem() method - item added");
		} else {
			response.setMessage(ShippingMessages.ITEM_AVAILABLE);
			logger.error("ShippingService.addItem() method - item available");
		}
		return response;
	}

	/**
	 * Update an item into available items list
	 * 
	 * @param itemRequest
	 * @return ItemResponse
	 */
	public ItemResponse updateItem(ItemRequest itemRequest) {
		logger.info("Invoke ShippingService.updateItem() method");
		ItemResponse response = new ItemResponse();
		if (shippingData.getItems().containsKey(itemRequest.getItem())) {
			Item item = shippingData.getItems().get(itemRequest.getItem());
			item.setSeller(itemRequest.getSeller());
			item.setCategory(itemRequest.getCategory());
			item.setMinmumPrice(itemRequest.getMinimumPrice());
			shippingData.getItems().put(itemRequest.getItem(), item);
			response.setMessage(ShippingMessages.ITEM_UPDATE);
			logger.info("ShippingService.updateItem() method - item update");
		} else {
			response.setMessage(ShippingMessages.ITEM_NOT_AVAILABLE);
			logger.error("ShippingService.updateItem() method - item not available");
		}
		return response;
	}

	/**
	 * Remove an item from available items list
	 * 
	 * @param title
	 * @return ItemResponse
	 */
	public ItemResponse deleteItem(String title) {
		logger.info("Invoke ShippingService.deleteItem() method");
		ItemResponse response = new ItemResponse();
		if (shippingData.getItems().containsKey(title)) {
			shippingData.getItems().remove(title);
			response.setMessage(ShippingMessages.ITEM_REMOVED);
			logger.info("ShippingService.deleteItem() method - item removed");
		} else {
			response.setMessage(ShippingMessages.ITEM_NOT_AVAILABLE);
			logger.error("ShippingService.deleteItem() method - item not available");
		}
		return response;
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
	public ShippingResponse checkEligibility(String title, String seller, int category, double price) {
		logger.info("Invoke ShippingService.checkEligibility() method");
		ShippingResponse response = new ShippingResponse();
		// Check for valid seller & title
		if (title == null || EMPTY.equals(title.trim()) || seller == null || EMPTY.equals(seller.trim())) {
			response.setMessage(ShippingMessages.NOT_ELIGIBLE);
			logger.error("ShippingService.checkEligibility() method - not eligible");
			return response;
		}

		// Check if seller belong to approved list
		if (!shippingData.getSellersEnrolled().contains(seller)) {
			response.setMessage(ShippingMessages.SELLER_NOT_APPROVED);
			logger.error("ShippingService.checkEligibility() method - seller not approved");
			return response;
		}

		// Check if title belong to approved category
		if (!shippingData.getApprovedCategories().contains(category)) {
			response.setMessage(ShippingMessages.ITEM_NOT_APPROVED);
			logger.error("ShippingService.checkEligibility() method - item not approved");
			return response;
		}

		// Check if item present in the item list
		if (!shippingData.getItems().containsKey(title)) {
			response.setMessage(ShippingMessages.ITEM_NOT_AVAILABLE);
			logger.error("ShippingService.checkEligibility() method - item not available");
			return response;
		}

		// Check if valid item is available
		Item item = shippingData.getItems().get(title);
		if (item == null) {
			response.setMessage(ShippingMessages.ITEM_NOT_VALID);
			logger.error("ShippingService.checkEligibility() method - item not valid");
			return response;
		}

		// Check if item has seller
		String sellerItem = item.getSeller();
		if (sellerItem != null && !EMPTY.equals(sellerItem.trim())
				&& sellerItem.trim().equalsIgnoreCase(seller.trim())) {
			// Check for item price
			if (price < item.getMinimumPrice()) {
				response.setMessage(ShippingMessages.ITEM_PRICE_CHECK_FAILED);
				logger.error("ShippingService.checkEligibility() method - item price check failed");
			} else {
				response.setMessage(ShippingMessages.ELIGIBLE);
				logger.info("ShippingService.checkEligibility() method - eligible");
			}
		} else {
			response.setMessage(ShippingMessages.SELLER_NOT_AVAILABLE_ITEM);
			logger.error("ShippingService.checkEligibility() method - seller not available item");
		}
		return response;
	}
}
