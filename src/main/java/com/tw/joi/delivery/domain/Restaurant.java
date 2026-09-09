package com.tw.joi.delivery.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Restaurant extends Outlet{
	
	private Set<FoodProduct> menu = new HashSet<>();
	private String cuisineCategory;
	private int deliveryTime;
	
	@Builder
	public Restaurant(String name, String description, String outletId, 
					  String cuisineCategory, int deliveryTime) {
		
		super(name, description, outletId);
		this.cuisineCategory = cuisineCategory;
		this.deliveryTime = deliveryTime;
		this.menu = new HashSet<>();
	}	
}
