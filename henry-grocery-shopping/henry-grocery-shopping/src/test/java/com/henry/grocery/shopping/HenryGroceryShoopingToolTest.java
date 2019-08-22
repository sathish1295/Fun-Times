package com.henry.grocery.shopping;


import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.henry.grocery.shopping.entity.ShoppingBasket;
import com.henry.grocery.shopping.enums.HenryStockDetails;
import com.henry.grocery.shopping.service.HenryGroceryOfferService;
import com.henry.grocery.shopping.service.HenryGroceryOfferServiceImpl;
import com.henry.grocery.shopping.service.HenryGroceryShoppingToolService;
import com.henry.grocery.shopping.service.HenryGroceryShoppingToolServiceImpl;

public class HenryGroceryShoopingToolTest {
	
	private static List<ShoppingBasket> shoppingBasketList = new ArrayList<>();
	
	private static HenryGroceryShoppingToolService henryGroceryToolService;
	
	private static HenryGroceryOfferService henryGroceryOfferService;
	
	 private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
		
	@BeforeClass
	public static void setShoppingItems() {
		ShoppingBasket shoppingBasketProductOne = new ShoppingBasket();
		ShoppingBasket shoppingBasketProductTwo = new ShoppingBasket();
		shoppingBasketProductOne.setItemName(HenryStockDetails.SOUP.productName);
		shoppingBasketProductOne.setCount(3);
		shoppingBasketList.add(shoppingBasketProductOne);
		shoppingBasketProductTwo.setItemName(HenryStockDetails.BREAD.productName);
		shoppingBasketProductTwo.setCount(2);
		shoppingBasketList.add(shoppingBasketProductTwo);	
		henryGroceryOfferService = new HenryGroceryOfferServiceImpl();
		henryGroceryToolService = new HenryGroceryShoppingToolServiceImpl(henryGroceryOfferService);
	}

	@Test
	public void test() {
		assertEquals(decimalFormat.format(3.15), decimalFormat.format(henryGroceryToolService
							.getTotalCost(shoppingBasketList, LocalDate.now().minusDays(1))));
	}

}
