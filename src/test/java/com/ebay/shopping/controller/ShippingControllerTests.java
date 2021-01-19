package com.ebay.shopping.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ebay.shopping.request.CategoryRequest;
import com.ebay.shopping.request.ItemRequest;
import com.ebay.shopping.request.SellerRequest;
import com.ebay.shopping.response.CategoryResponse;
import com.ebay.shopping.response.ItemResponse;
import com.ebay.shopping.response.SellerResponse;
import com.ebay.shopping.response.ShippingResponse;
import com.ebay.shopping.service.ShippingService;
import com.ebay.shopping.util.ShippingMessages;
import com.ebay.shopping.valueobject.Item;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShippingControllerTests {

	@Mock
	private ShippingService shippingService;

	@InjectMocks
	private ShippingController shippingController;

	@Test
	public void testGetCategories() {
		Mockito.when(shippingService.getCategories()).thenReturn(categoryResponse());
		Assert.assertNotNull(shippingController.getCategories());
		Assert.assertEquals(5, shippingController.getCategories().getCategories().size());
		Assert.assertNotEquals(3, shippingController.getCategories().getCategories().size());
	}

	@Test
	public void testAddCategories_Available() {
		CategoryResponse response = categoryResponse();
		response.setMessage(ShippingMessages.CATEGORY_AVAILABLE);
		Mockito.when(shippingService.addCategory(Mockito.any())).thenReturn(response);
		CategoryRequest request = new CategoryRequest();
		request.setCategory(5);
		Assert.assertEquals(ShippingMessages.CATEGORY_AVAILABLE, shippingController.addCategory(request).getMessage());
	}

	@Test
	public void testAddCategories_Added() {
		CategoryResponse response = categoryResponse();
		response.setMessage(ShippingMessages.CATEGORY_ADDED);
		Mockito.when(shippingService.addCategory(Mockito.any())).thenReturn(response);
		CategoryRequest request = new CategoryRequest();
		request.setCategory(6);
		Assert.assertEquals(ShippingMessages.CATEGORY_ADDED, shippingController.addCategory(request).getMessage());
	}

	@Test
	public void testDeteleCategories_Not_Available() {
		CategoryResponse response = categoryResponse();
		response.setMessage(ShippingMessages.CATEGORY_NOT_AVAILABLE);
		Mockito.when(shippingService.deleteCategory(Mockito.anyInt())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.CATEGORY_NOT_AVAILABLE, shippingController.deleteCategory(6).getMessage());
	}

	@Test
	public void testDeteleCategories_Removed() {
		CategoryResponse response = categoryResponse();
		response.setMessage(ShippingMessages.CATEGORY_REMOVED);
		Mockito.when(shippingService.deleteCategory(Mockito.anyInt())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.CATEGORY_REMOVED, shippingController.deleteCategory(5).getMessage());
	}

	@Test
	public void testGetSellers() {
		Mockito.when(shippingService.getSellers()).thenReturn(sellerResponse());
		Assert.assertNotNull(shippingController.getSellers());
		Assert.assertEquals(5, shippingController.getSellers().getSellers().size());
		Assert.assertNotEquals(3, shippingController.getSellers().getSellers().size());
	}

	@Test
	public void testAddSeller_Available() {
		SellerResponse response = sellerResponse();
		response.setMessage(ShippingMessages.SELLER_AVAILABLE);
		Mockito.when(shippingService.addSeller(Mockito.any())).thenReturn(response);
		SellerRequest request = new SellerRequest();
		request.setSeller("seller5");
		Assert.assertEquals(ShippingMessages.SELLER_AVAILABLE, shippingController.addSeller(request).getMessage());
	}

	@Test
	public void testAddSeller_Added() {
		SellerResponse response = sellerResponse();
		response.setMessage(ShippingMessages.SELLER_ADDED);
		Mockito.when(shippingService.addSeller(Mockito.any())).thenReturn(response);
		SellerRequest request = new SellerRequest();
		request.setSeller("seller6");
		Assert.assertEquals(ShippingMessages.SELLER_ADDED, shippingController.addSeller(request).getMessage());
	}

	@Test
	public void testDeteleSeller_Available() {
		SellerResponse response = sellerResponse();
		response.setMessage(ShippingMessages.SELLER_NOT_AVAILABLE);
		Mockito.when(shippingService.deleteSeller(Mockito.anyString())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.SELLER_NOT_AVAILABLE,
				shippingController.deleteSeller("seller6").getMessage());
	}

	@Test
	public void testDeteleSeller_Removed() {
		SellerResponse response = sellerResponse();
		response.setMessage(ShippingMessages.SELLER_REMOVED);
		Mockito.when(shippingService.deleteSeller(Mockito.anyString())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.SELLER_REMOVED, shippingController.deleteSeller("seller5").getMessage());
	}

	@Test
	public void testGetItems() {
		Mockito.when(shippingService.getItems()).thenReturn(itemResponse());
		Assert.assertNotNull(shippingController.getItems());
		Assert.assertEquals(5, shippingController.getItems().getItems().size());
		Assert.assertNotEquals(3, shippingController.getItems().getItems().size());
	}

	@Test
	public void testAddItems_Available() {
		ItemResponse response = itemResponse();
		response.setMessage(ShippingMessages.ITEM_AVAILABLE);
		Mockito.when(shippingService.addItem(Mockito.any())).thenReturn(response);
		ItemRequest request = new ItemRequest();
		request.setItem("item5");
		request.setSeller("seller5");
		request.setCategory(5);
		request.setMinimumPrice(50);
		Assert.assertEquals(ShippingMessages.ITEM_AVAILABLE, shippingController.addItem(request).getMessage());
	}

	@Test
	public void testAddItems_Added() {
		ItemResponse response = itemResponse();
		response.setMessage(ShippingMessages.ITEM_ADDED);
		Mockito.when(shippingService.addItem(Mockito.any())).thenReturn(response);
		ItemRequest request = new ItemRequest();
		request.setItem("item6");
		request.setSeller("seller6");
		request.setCategory(6);
		request.setMinimumPrice(60);
		Assert.assertEquals(ShippingMessages.ITEM_ADDED, shippingController.addItem(request).getMessage());
	}

	@Test
	public void testUpdateItems_Not_Available() {
		ItemResponse response = itemResponse();
		response.setMessage(ShippingMessages.ITEM_NOT_AVAILABLE);
		Mockito.when(shippingService.updateItem(Mockito.any())).thenReturn(response);
		ItemRequest request = new ItemRequest();
		request.setSeller("item6");
		request.setSeller("seller6");
		request.setCategory(6);
		request.setMinimumPrice(60);
		Assert.assertEquals(ShippingMessages.ITEM_NOT_AVAILABLE, shippingController.updateItem(request).getMessage());
	}

	@Test
	public void testUpdateItems_Update() {
		ItemResponse response = itemResponse();
		response.setMessage(ShippingMessages.ITEM_UPDATE);
		Mockito.when(shippingService.updateItem(Mockito.any())).thenReturn(response);
		ItemRequest request = new ItemRequest();
		request.setItem("item5");
		request.setSeller("seller6");
		request.setCategory(6);
		request.setMinimumPrice(60);
		Assert.assertEquals(ShippingMessages.ITEM_UPDATE, shippingController.updateItem(request).getMessage());
	}

	@Test
	public void testDeteleItems_Not_Available() {
		ItemResponse response = itemResponse();
		response.setMessage(ShippingMessages.ITEM_NOT_AVAILABLE);
		Mockito.when(shippingService.deleteItem(Mockito.anyString())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.ITEM_NOT_AVAILABLE, shippingController.deleteItem("item6").getMessage());
	}

	@Test
	public void testDeteleItems_Removed() {
		ItemResponse response = itemResponse();
		response.setMessage(ShippingMessages.ITEM_REMOVED);
		Mockito.when(shippingService.deleteItem(Mockito.anyString())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.ITEM_REMOVED, shippingController.deleteItem("item5").getMessage());
	}

	@Test
	public void testCheckEligibility_Not_Eligible_1() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.NOT_ELIGIBLE);
		Mockito.when(shippingService.checkEligibility(Mockito.any(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.NOT_ELIGIBLE,
				shippingController.checkEligibility(null, "seller1", 1, 10).getMessage());
	}

	@Test
	public void testCheckEligibility_Not_Eligible_2() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.NOT_ELIGIBLE);
		Mockito.when(shippingService.checkEligibility(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.NOT_ELIGIBLE,
				shippingController.checkEligibility("", "seller1", 1, 10).getMessage());
	}

	@Test
	public void testCheckEligibility_Not_Eligible_3() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.NOT_ELIGIBLE);
		Mockito.when(shippingService.checkEligibility(Mockito.anyString(), Mockito.any(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.NOT_ELIGIBLE,
				shippingController.checkEligibility("item1", null, 1, 10).getMessage());
	}

	@Test
	public void testCheckEligibility_Not_Eligible_4() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.NOT_ELIGIBLE);
		Mockito.when(shippingService.checkEligibility(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.NOT_ELIGIBLE,
				shippingController.checkEligibility("item1", "", 1, 10).getMessage());
	}

	@Test
	public void testCheckEligibility_Seller_Not_Approved() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.SELLER_NOT_APPROVED);
		Mockito.when(shippingService.checkEligibility(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.SELLER_NOT_APPROVED,
				shippingController.checkEligibility("item1", "seller6", 1, 10).getMessage());
	}

	@Test
	public void testCheckEligibility_Item_Not_Approved() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.ITEM_NOT_APPROVED);
		Mockito.when(shippingService.checkEligibility(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.ITEM_NOT_APPROVED,
				shippingController.checkEligibility("item1", "seller1", 6, 10).getMessage());
	}

	@Test
	public void testCheckEligibility_Item_Not_Available() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.ITEM_NOT_AVAILABLE);
		Mockito.when(shippingService.checkEligibility(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.ITEM_NOT_AVAILABLE,
				shippingController.checkEligibility("item6", "seller5", 1, 10).getMessage());
	}

	@Test
	public void testCheckEligibility_Seller_Not_Available_Item() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.SELLER_NOT_AVAILABLE_ITEM);
		Mockito.when(shippingService.checkEligibility(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.SELLER_NOT_AVAILABLE_ITEM,
				shippingController.checkEligibility("item1", "seller2", 1, 10).getMessage());
	}

	@Test
	public void testCheckEligibility_Item_Price_Check_Failed() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.ITEM_PRICE_CHECK_FAILED);
		Mockito.when(shippingService.checkEligibility(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.ITEM_PRICE_CHECK_FAILED,
				shippingController.checkEligibility("item1", "seller1", 1, 1).getMessage());
	}

	@Test
	public void testCheckEligibility_Eligible() {
		ShippingResponse response = new ShippingResponse();
		response.setMessage(ShippingMessages.ELIGIBLE);
		Mockito.when(shippingService.checkEligibility(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyDouble())).thenReturn(response);
		Assert.assertEquals(ShippingMessages.ELIGIBLE,
				shippingController.checkEligibility("item1", "seller1", 1, 100).getMessage());
	}

	/**
	 * Test data for pre-approved categories
	 * 
	 * @return pre-approved categories
	 */
	private CategoryResponse categoryResponse() {
		CategoryResponse response = new CategoryResponse();
		Set<Integer> approvedCategories = new HashSet<>();
		approvedCategories.add(1);
		approvedCategories.add(2);
		approvedCategories.add(3);
		approvedCategories.add(4);
		approvedCategories.add(5);
		response.setCategories(approvedCategories);
		return response;
	}

	/**
	 * Test data for seller response
	 * 
	 * @return seller response
	 */
	private SellerResponse sellerResponse() {
		SellerResponse response = new SellerResponse();
		Set<String> enrolledSellers = new HashSet<>();
		enrolledSellers.add("seller1");
		enrolledSellers.add("seller2");
		enrolledSellers.add("seller3");
		enrolledSellers.add("seller4");
		enrolledSellers.add("seller5");
		response.setSellers(enrolledSellers);
		return response;
	}

	/**
	 * Test data for items response
	 * 
	 * @return items response
	 */
	private ItemResponse itemResponse() {
		ItemResponse response = new ItemResponse();
		Map<String, Item> availableItems = new HashMap<>();
		availableItems.put("item1", new Item("seller1", 1, 10));
		availableItems.put("item2", new Item("seller2", 2, 20));
		availableItems.put("item3", new Item("seller3", 3, 30));
		availableItems.put("item4", new Item("seller4", 4, 40));
		availableItems.put("item5", new Item("seller5", 5, 50));
		response.setItems(availableItems);
		return response;
	}
}
