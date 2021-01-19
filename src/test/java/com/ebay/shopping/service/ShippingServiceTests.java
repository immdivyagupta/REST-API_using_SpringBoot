package com.ebay.shopping.service;

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

import com.ebay.shopping.data.ShippingData;
import com.ebay.shopping.request.CategoryRequest;
import com.ebay.shopping.request.ItemRequest;
import com.ebay.shopping.request.SellerRequest;
import com.ebay.shopping.service.ShippingService;
import com.ebay.shopping.util.ShippingMessages;
import com.ebay.shopping.valueobject.Item;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShippingServiceTests {

	@Mock
	private ShippingData shippingData;

	@InjectMocks
	ShippingService shippingService;

	@Test
	public void testGetCategories() {
		Mockito.when(shippingData.getApprovedCategories()).thenReturn(approvedCategories());
		Assert.assertNotNull(shippingService.getCategories());
		Assert.assertEquals(5, shippingService.getCategories().getCategories().size());
		Assert.assertNotEquals(3, shippingService.getCategories().getCategories().size());
	}

	@Test
	public void testPostCategories() {
		Mockito.when(shippingData.getApprovedCategories()).thenReturn(approvedCategories());
		CategoryRequest request = new CategoryRequest();
		request.setCategory(5);
		Assert.assertEquals(ShippingMessages.CATEGORY_AVAILABLE, shippingService.addCategory(request).getMessage());
		Assert.assertEquals(5, shippingService.getCategories().getCategories().size());
		request.setCategory(6);
		Assert.assertEquals(ShippingMessages.CATEGORY_ADDED, shippingService.addCategory(request).getMessage());
		Assert.assertEquals(6, shippingService.getCategories().getCategories().size());
	}

	@Test
	public void testDeteleCategories() {
		Mockito.when(shippingData.getApprovedCategories()).thenReturn(approvedCategories());
		Assert.assertEquals(ShippingMessages.CATEGORY_NOT_AVAILABLE, shippingService.deleteCategory(6).getMessage());
		Assert.assertEquals(5, shippingService.getCategories().getCategories().size());
		Assert.assertEquals(ShippingMessages.CATEGORY_REMOVED, shippingService.deleteCategory(5).getMessage());
		Assert.assertEquals(4, shippingService.getCategories().getCategories().size());
	}

	@Test
	public void testGetSellers() {
		Mockito.when(shippingData.getSellersEnrolled()).thenReturn(enrolledSellers());
		Assert.assertNotNull(shippingService.getSellers());
		Assert.assertEquals(5, shippingService.getSellers().getSellers().size());
		Assert.assertNotEquals(3, shippingService.getSellers().getSellers().size());
	}

	@Test
	public void testAddSeller() {
		Mockito.when(shippingData.getSellersEnrolled()).thenReturn(enrolledSellers());
		SellerRequest request = new SellerRequest();
		request.setSeller("seller5");
		Assert.assertEquals(ShippingMessages.SELLER_AVAILABLE, shippingService.addSeller(request).getMessage());
		Assert.assertEquals(5, shippingService.getSellers().getSellers().size());
		request.setSeller("seller6");
		Assert.assertEquals(ShippingMessages.SELLER_ADDED, shippingService.addSeller(request).getMessage());
		Assert.assertEquals(6, shippingService.getSellers().getSellers().size());
	}

	@Test
	public void testDeteleSeller() {
		Mockito.when(shippingData.getSellersEnrolled()).thenReturn(enrolledSellers());
		Assert.assertEquals(ShippingMessages.SELLER_NOT_AVAILABLE,
				shippingService.deleteSeller("seller6").getMessage());
		Assert.assertEquals(5, shippingService.getSellers().getSellers().size());
		Assert.assertEquals(ShippingMessages.SELLER_REMOVED, shippingService.deleteSeller("seller5").getMessage());
		Assert.assertEquals(4, shippingService.getSellers().getSellers().size());
	}

	@Test
	public void testGetItems() {
		Mockito.when(shippingData.getItems()).thenReturn(availableItems());
		Assert.assertNotNull(shippingService.getItems());
		Assert.assertEquals(5, shippingService.getItems().getItems().size());
		Assert.assertNotEquals(3, shippingService.getItems().getItems().size());
	}

	@Test
	public void testAddItems() {
		Mockito.when(shippingData.getItems()).thenReturn(availableItems());
		ItemRequest request = new ItemRequest();
		request.setItem("item5");
		request.setSeller("seller5");
		request.setCategory(5);
		request.setMinimumPrice(50);
		Assert.assertEquals(ShippingMessages.ITEM_AVAILABLE, shippingService.addItem(request).getMessage());
		Assert.assertEquals(5, shippingService.getItems().getItems().size());
		request.setItem("item6");
		request.setSeller("seller6");
		request.setCategory(6);
		request.setMinimumPrice(60);
		Assert.assertEquals(ShippingMessages.ITEM_ADDED, shippingService.addItem(request).getMessage());
		Assert.assertEquals(6, shippingService.getItems().getItems().size());
	}

	@Test
	public void testUpdateItems() {
		Mockito.when(shippingData.getItems()).thenReturn(availableItems());
		ItemRequest request = new ItemRequest();
		request.setSeller("item6");
		request.setSeller("seller6");
		request.setCategory(6);
		request.setMinimumPrice(60);
		Assert.assertEquals(ShippingMessages.ITEM_NOT_AVAILABLE, shippingService.updateItem(request).getMessage());
		Assert.assertEquals(5, shippingService.getItems().getItems().size());
		request.setItem("item5");
		request.setSeller("seller6");
		request.setCategory(6);
		request.setMinimumPrice(60);
		Assert.assertEquals(ShippingMessages.ITEM_UPDATE, shippingService.updateItem(request).getMessage());
		Assert.assertEquals(5, shippingService.getItems().getItems().size());
	}

	@Test
	public void testDeteleItems() {
		Mockito.when(shippingData.getItems()).thenReturn(availableItems());
		Assert.assertEquals(ShippingMessages.ITEM_NOT_AVAILABLE, shippingService.deleteItem("item6").getMessage());
		Assert.assertEquals(5, shippingService.getItems().getItems().size());
		Assert.assertEquals(ShippingMessages.ITEM_REMOVED, shippingService.deleteItem("item5").getMessage());
		Assert.assertEquals(4, shippingService.getItems().getItems().size());
	}

	@Test
	public void testCheckEligibility() {
		Mockito.when(shippingData.getApprovedCategories()).thenReturn(approvedCategories());
		Mockito.when(shippingData.getSellersEnrolled()).thenReturn(enrolledSellers());
		Mockito.when(shippingData.getItems()).thenReturn(availableItems());
		Assert.assertEquals(ShippingMessages.NOT_ELIGIBLE,
				shippingService.checkEligibility(null, "seller1", 1, 10).getMessage());
		Assert.assertEquals(ShippingMessages.NOT_ELIGIBLE,
				shippingService.checkEligibility("", "seller1", 1, 10).getMessage());
		Assert.assertEquals(ShippingMessages.NOT_ELIGIBLE,
				shippingService.checkEligibility("item1", null, 1, 10).getMessage());
		Assert.assertEquals(ShippingMessages.NOT_ELIGIBLE,
				shippingService.checkEligibility("item1", "", 1, 10).getMessage());
		Assert.assertEquals(ShippingMessages.SELLER_NOT_APPROVED,
				shippingService.checkEligibility("item1", "seller6", 1, 10).getMessage());
		Assert.assertEquals(ShippingMessages.ITEM_NOT_APPROVED,
				shippingService.checkEligibility("item1", "seller1", 6, 10).getMessage());
		Assert.assertEquals(ShippingMessages.ITEM_NOT_AVAILABLE,
				shippingService.checkEligibility("item6", "seller5", 1, 10).getMessage());
		Assert.assertEquals(ShippingMessages.SELLER_NOT_AVAILABLE_ITEM,
				shippingService.checkEligibility("item1", "seller2", 1, 10).getMessage());
		Assert.assertEquals(ShippingMessages.ITEM_PRICE_CHECK_FAILED,
				shippingService.checkEligibility("item1", "seller1", 1, 1).getMessage());
		Assert.assertEquals(ShippingMessages.ELIGIBLE,
				shippingService.checkEligibility("item1", "seller1", 1, 100).getMessage());
	}

	/**
	 * Test data for pre-approved categories
	 * 
	 * @return pre-approved categories
	 */
	private Set<Integer> approvedCategories() {
		Set<Integer> approvedCategories = new HashSet<>();
		approvedCategories.add(1);
		approvedCategories.add(2);
		approvedCategories.add(3);
		approvedCategories.add(4);
		approvedCategories.add(5);
		return approvedCategories;
	}

	/**
	 * Test data for enrolled sellers
	 * 
	 * @return enrolled sellers
	 */
	private Set<String> enrolledSellers() {
		Set<String> enrolledSellers = new HashSet<>();
		enrolledSellers.add("seller1");
		enrolledSellers.add("seller2");
		enrolledSellers.add("seller3");
		enrolledSellers.add("seller4");
		enrolledSellers.add("seller5");
		return enrolledSellers;
	}

	/**
	 * Test data for available items
	 * 
	 * @return available items
	 */
	private Map<String, Item> availableItems() {
		Map<String, Item> availableItems = new HashMap<>();
		availableItems.put("item1", new Item("seller1", 1, 10));
		availableItems.put("item2", new Item("seller2", 2, 20));
		availableItems.put("item3", new Item("seller3", 3, 30));
		availableItems.put("item4", new Item("seller4", 4, 40));
		availableItems.put("item5", new Item("seller5", 5, 50));
		return availableItems;
	}
}
