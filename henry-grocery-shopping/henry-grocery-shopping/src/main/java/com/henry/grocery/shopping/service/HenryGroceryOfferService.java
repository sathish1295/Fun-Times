package com.henry.grocery.shopping.service;

import java.time.LocalDate;
import java.util.List;

import com.henry.grocery.shopping.entity.ShoppingBasket;

/**
 * HenryGroceryOfferService is an interface for getting the offer values of the products.
 * 
 * @author SathishKumar
 * @since Aug 2019
 *
 */
public interface HenryGroceryOfferService {
	
	/**
	 * applyOffers method checks whether the product is eligible for offers and calculates the amount to discounted.
	 * 
	 * @param shoppingBasketValues
	 * 				products purchased by the customer.
	 * @param shoppingDate
	 * 				date of purchase.
	 * @return offer amount to be discounted
	 */
	public double applyOffers(List<ShoppingBasket> shoppingBasketValues, LocalDate shoppingDate);

}
