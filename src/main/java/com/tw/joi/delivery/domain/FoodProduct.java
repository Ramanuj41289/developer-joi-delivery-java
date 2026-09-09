package com.tw.joi.delivery.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodProduct extends Product {
    
    private BigDecimal sellingPrice;      
    private String cuisineType;
    private int preparationTime;          
    private int availableStock;           
    private int threshold;                
    private BigDecimal discount;
    private BigDecimal rating;
    private Boolean isVegetarian;         
    private Restaurant restaurant;

    @Builder
    public FoodProduct(String productId, String productName, BigDecimal mrp, 
                       BigDecimal sellingPrice, String cuisineType, int preparationTime,
                       int availableStock, int threshold, BigDecimal discount,  // ✅ Added
                       BigDecimal rating, Boolean isVegetarian, Restaurant restaurant) {
        super(productId, productName, mrp);
        this.sellingPrice = sellingPrice;
        this.cuisineType = cuisineType;
        this.preparationTime = preparationTime;
        this.availableStock = availableStock;  
        this.threshold = threshold;             
        this.discount = discount;
        this.rating = rating;
        this.isVegetarian = isVegetarian;
        this.restaurant = restaurant;
    }

}