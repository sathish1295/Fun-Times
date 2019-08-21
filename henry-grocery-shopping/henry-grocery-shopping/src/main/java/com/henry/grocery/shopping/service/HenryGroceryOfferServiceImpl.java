package com.henry.grocery.shopping.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.henry.grocery.shopping.entity.HenryGroceryOffers;
import com.henry.grocery.shopping.entity.ShoppingBasket;
import com.henry.grocery.shopping.enums.HenryStockDetails;

/**
 * HenryGroceryOfferServiceImpl implements the HenryGroceryOfferService interface.
 * 
 * @author SathishKumar
 * @since Aug 2019
 *
 */
public class HenryGroceryOfferServiceImpl implements HenryGroceryOfferService {

	@Override
	public double applyOffers(List<ShoppingBasket> shoppingBasketValues, LocalDate shoppingDate) {
		Map<String,HenryGroceryOffers> henryGroceryOffers = getOffersList();
		Set<String> offerApplicableProducts = henryGroceryOffers.keySet();
		return shoppingBasketValues.stream().filter(cartItem->offerApplicableProducts.contains(cartItem.getItemName()))
						.mapToDouble(cartItem->{
							HenryGroceryOffers productOffer = henryGroceryOffers.get(cartItem.getItemName());
							double discountPrice= 0;
							if(checkOfferPeriod(shoppingDate, productOffer) 
									&& cartItem.getCount()>= productOffer.getMinimumCount()) {
								if(productOffer.getDiscountPercentage() > 0) {
									discountPrice = productOffer.isRepeatOffer() ? calculateRepeatedDiscount(productOffer.getMinimumCount(),cartItem.getCount(),
											productOffer.getDiscountPercentage(),HenryStockDetails.valueOf(cartItem.getItemName()).getPrice()) : 
										(HenryStockDetails.valueOf(cartItem.getItemName()).getPrice() *cartItem.getCount()) * (productOffer.getDiscountPercentage()*0.01);
								}
								 discountPrice+= applyOtherOffers(productOffer,shoppingBasketValues);
							}
							return discountPrice;
						}).sum();
	}
	
	/**
	 * applyOtherOffers method applies the offer value on other products in favour of purchased product.
	 * 
	 * @param henryGroceryOffers
	 * 			offer details of the product.
	 * @param shoppingBasketValues
	 * 			cart details of the purchased products
	 * @return offer value applied on other products.
	 */
	private double applyOtherOffers(HenryGroceryOffers henryGroceryOffers, List<ShoppingBasket> shoppingBasketValues) {
		Map<String,Integer> cartProductsCountMap = shoppingBasketValues.stream().
				collect(Collectors.toMap(ShoppingBasket::getItemName, ShoppingBasket::getCount));
		Map<String,Integer> otherOffers = henryGroceryOffers.getOtherProductOffers();
		return	otherOffers.keySet().stream().filter(otherProduct-> cartProductsCountMap.keySet().contains(otherProduct))
				.mapToDouble(otherProduct->{
					int discountPercentage = otherOffers.get(otherProduct);
					return henryGroceryOffers.isRepeatOffer() ? 
							calculateRepeatedDiscount(henryGroceryOffers.getMinimumCount(),cartProductsCountMap.get(otherProduct),
									discountPercentage,HenryStockDetails.valueOf(otherProduct).getPrice()) :  
						(HenryStockDetails.valueOf(otherProduct).getPrice()* cartProductsCountMap.get(otherProduct))* (discountPercentage*0.01);
				}).sum();
	}
	
	/**
	 * calculateRepeatedDiscount method is executed when there is an offer for every n purchase of a product.
	 * 
	 * @param minimumCount
	 * 			minimum count of the product for the offer to be applied.
	 * @param totalCount
	 * 			total amount of products purchased.
	 * @param discountPercentage
	 * 			percentage of amount to be discounted.
	 * @param price
	 * 			actual price of the product.
	 * @return total discount of the product for n purchases.
	 */
	private double calculateRepeatedDiscount(int minimumCount, int totalCount, int discountPercentage, double price) {
		int count = totalCount>=minimumCount ? totalCount/minimumCount : totalCount;
		return (count*price)*discountPercentage*0.01;
	}
	
	/**
	 * checkOfferPeriod method is used to validate whether the shopping date is present in the range of offer period.
	 * 
	 * @param shoppingDate
	 * 			date of product purchase
	 * @param productOffer
	 * 			 offer details
	 * @return true if the shopping date met the offer period criteria
	 */
	private boolean checkOfferPeriod(LocalDate shoppingDate, HenryGroceryOffers productOffer) {
		return shoppingDate.isAfter(productOffer.getStartDate().minusDays(1)) && shoppingDate.isBefore(productOffer.getEndDate().plusDays(1));
	}

	/**
	 * getOffersList retreives the current offers available in Henry's Grocery Store.
	 * 
	 * @return offersMap{Key: productName, Value: OfferDetails}
	 */
	private Map<String,HenryGroceryOffers> getOffersList() {
		Map<String,HenryGroceryOffers> offersMap = new HashMap<>();
		
		HenryGroceryOffers soupOffer = new HenryGroceryOffers();
		soupOffer.setItemName(HenryStockDetails.SOUP.productName);
		soupOffer.setDiscountPercentage(0);
		soupOffer.setMinimumCount(2);
		soupOffer.setStartDate(LocalDate.now().minusDays(1));
		soupOffer.setEndDate(LocalDate.now().plusDays(6));
		soupOffer.setRepeatOffer(true);
		Map<String,Integer> otherProductOfferMap = new HashMap<>();
		otherProductOfferMap.put(HenryStockDetails.BREAD.productName, 50);
		soupOffer.setOtherProductOffers(otherProductOfferMap);
		offersMap.put(HenryStockDetails.SOUP.productName, soupOffer);
		
		HenryGroceryOffers appleOffer = new HenryGroceryOffers();
		appleOffer.setItemName(HenryStockDetails.APPLE.productName);
		appleOffer.setMinimumCount(1);
		appleOffer.setDiscountPercentage(10);
		appleOffer.setStartDate(LocalDate.now().plusDays(3));
		appleOffer.setEndDate(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
		appleOffer.setRepeatOffer(false);
		appleOffer.setOtherProductOffers(new HashMap<>());
		offersMap.put(HenryStockDetails.APPLE.productName, appleOffer);;
		
		return offersMap;
	}

}
