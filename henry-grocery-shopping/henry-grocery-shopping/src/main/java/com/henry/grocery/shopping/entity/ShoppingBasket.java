package com.henry.grocery.shopping.entity;

import lombok.Data;

/**
 * ShoppingBasket is an entity for holding the values of grocery items and it's count purchased by customer
 * 
 * @author SathishKumar
 * @since Aug 2019
 *
 */
@Data
public class ShoppingBasket {

	private String itemName;
	
	private int count;

}
