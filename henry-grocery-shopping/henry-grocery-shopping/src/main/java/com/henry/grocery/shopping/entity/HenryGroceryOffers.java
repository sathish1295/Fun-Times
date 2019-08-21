package com.henry.grocery.shopping.entity;

import java.time.LocalDate;
import java.util.Map;

import lombok.Data;

/**
 * HenryGroceryOffers is an entity class for holding the offer details of the products.
 * 
 * @author SathishKumar
 * @since Aug 2019
 *
 */
@Data
public class HenryGroceryOffers {
	
	public String itemName;
	
	public int minimumCount;
	
	public int discountPercentage;
	
	public LocalDate startDate;
	
	public LocalDate endDate;
	
	public boolean repeatOffer;
	
	public Map<String,Integer> otherProductOffers;
}
