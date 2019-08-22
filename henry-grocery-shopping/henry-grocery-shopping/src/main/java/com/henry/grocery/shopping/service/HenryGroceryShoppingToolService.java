package com.henry.grocery.shopping.service;

import java.time.LocalDate;
import java.util.List;

import com.henry.grocery.shopping.entity.ShoppingBasket;

/**
 * HenryGroceryShoppingToolService is the service for calculating the total purchased amount and apply offers.
 * 
 * @author SathishKumar
 * @since Aug 2019
 */
public interface HenryGroceryShoppingToolService {
	
	/**
	 * getTotalCost method caculates the total amount to be paid b y the customer for the shopped products.
	 * 
	 * @param shoppingBasketList
	 * 				contains the products and it's count purchased by the customer.
	 * @param shoppingDate
	 * 				date of purchase
	 */
	public double getTotalCost(List<ShoppingBasket> shoppingBasketList, LocalDate shoppingDate);

}
