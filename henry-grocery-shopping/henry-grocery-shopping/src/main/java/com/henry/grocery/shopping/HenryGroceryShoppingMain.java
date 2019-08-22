package com.henry.grocery.shopping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.henry.grocery.shopping.entity.ShoppingBasket;
import com.henry.grocery.shopping.enums.HenryStockDetails;
import com.henry.grocery.shopping.service.HenryGroceryOfferService;
import com.henry.grocery.shopping.service.HenryGroceryOfferServiceImpl;
import com.henry.grocery.shopping.service.HenryGroceryShoppingToolService;
import com.henry.grocery.shopping.service.HenryGroceryShoppingToolServiceImpl;

/**
 * HenryGroceryShoppingMain is the main class for shopping tool to calculate the price of products brought by customers.
 * 
 * @author SathishKumar
 * @since Aug 2019
 *
 */
public class HenryGroceryShoppingMain {
		
	public static final String UNIT_VALUE = "productUnit";
	
	public static final String PRODUCT_NAME = "productName";
	
	public static final HenryGroceryOfferService henryGroceryOfferService = new HenryGroceryOfferServiceImpl();
	
	public static final HenryGroceryShoppingToolService henryGroceryShoppingToolService = new HenryGroceryShoppingToolServiceImpl(henryGroceryOfferService);	

	/**
	 * get the product details from the customers.
	 * 
	 * @param arguments
	 */
	public static void main(String[] args) {
		
		List<ShoppingBasket> shoppingBasketList = new ArrayList<>();
		
		Scanner scanner= new Scanner(System.in);		
		HenryStockDetails[] henryStockDetails = HenryStockDetails.values();
		
		System.out.println("*********Welcome To Henry's Grocery Shopping*************");
		Arrays.stream(henryStockDetails).forEach(stockItem -> {
			ShoppingBasket shopItem = new ShoppingBasket();
			String itemName = stockItem.productName;
			System.out.println("How many "+ stockItem.productUnit + " of "+ itemName + "?");
			int count = scanner.nextInt();
			if(count>0) {
			shopItem.setItemName(itemName);
			shopItem.setCount(count);
			shoppingBasketList.add(shopItem);
			}
		});		
		System.out.println("Enter the shopping date in DD//MM//YYYY format");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		LocalDate shoppingDate = LocalDate.parse(scanner.next(),formatter);
		henryGroceryShoppingToolService.getTotalCost(shoppingBasketList,shoppingDate);
		System.out.println("Thanks for Shopping");
	}

}
