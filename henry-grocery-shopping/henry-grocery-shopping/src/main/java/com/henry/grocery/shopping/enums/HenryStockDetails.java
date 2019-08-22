package com.henry.grocery.shopping.enums;

import lombok.Getter;

/**
 * HenryStockDetails is an enum class holds the value of stock available in Henry's Grocery Shopping and its price per unit.
 * @author SathishKumar
 * @since Aug 2019
 *
 */
@Getter
public enum HenryStockDetails {
	
	SOUP("SOUP","tin",0.65),
	BREAD("BREAD","loaf",0.80),
	MILK("MILK","bottle",1.30),
	APPLE("APPLE","single",0.10);
	
	public String productName;
	
	public final String productUnit;
	
	public final double price;
	
	private HenryStockDetails(String productName, String productUnit , double price) {
		this.productName = productName;
		this.productUnit = productUnit;
		this.price = price;
	}
	
}
