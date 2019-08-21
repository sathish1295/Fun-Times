package com.henry.grocery.shopping.service;

import java.time.LocalDate;
import java.util.List;

import com.henry.grocery.shopping.entity.ShoppingBasket;
import com.henry.grocery.shopping.enums.HenryStockDetails;

/**
 * HenryGroceryShoppingToolServiceImpl implements the HenryGroceryShoppingToolService interface for calculating shopping amount
 * 
 * @author SathishKumar
 * @since Aug 2019
 *
 */
public class HenryGroceryShoppingToolServiceImpl implements HenryGroceryShoppingToolService {
	
	private HenryGroceryOfferService henryGroceryOfferService;
	
	public HenryGroceryShoppingToolServiceImpl(HenryGroceryOfferService henryGroceryOfferService){
		this.henryGroceryOfferService = henryGroceryOfferService;
	}
	 
	@Override
	public double getTotalCost(List<ShoppingBasket> shoppingBasketList, LocalDate shoppingDate) {
		double totalPrice = shoppingBasketList.stream()
				.mapToDouble(item-> HenryStockDetails.valueOf(item.getItemName()).price * item.getCount()).sum();	
		double discountPrice = henryGroceryOfferService.applyOffers(shoppingBasketList, shoppingDate);
		double actualPrice = totalPrice - discountPrice;
		System.out.println("Total Shopping Amount:"+String.format("%.2f", totalPrice));
		System.out.println("Offer Value:"+String.format("%.2f",discountPrice));
		System.out.println("Total Amount to be paid:"+String.format("%.2f",actualPrice));
		return actualPrice;
	}
}
