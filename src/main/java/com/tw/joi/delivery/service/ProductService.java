package com.tw.joi.delivery.service;

import com.tw.joi.delivery.domain.FoodProduct;
import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.domain.Product;
import com.tw.joi.delivery.seedData.SeedData;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {

    private final List<GroceryProduct> groceryProducts = SeedData.groceryProducts;
    private final List<FoodProduct> foodProducts = SeedData.foodProducts;

    
    public Product getProduct(String productId, String outletId) {
        
        GroceryProduct groceryProduct = groceryProducts.stream()
            .filter(gp -> gp.getProductId().equals(productId)
                    && gp.getStore().getOutletId().equals(outletId))
            .findFirst()
            .orElse(null);

        if (groceryProduct != null) {
            log.info("✅ Found Grocery Product: {} from store: {}", productId, outletId);
            return groceryProduct;  // ✅ Return as Product
        }

        
        FoodProduct foodProduct = foodProducts.stream()
            .filter(fp -> fp.getProductId().equals(productId)
                    && fp.getRestaurant().getOutletId().equals(outletId))
            .findFirst()
            .orElse(null);

        if (foodProduct != null) {
            log.info("✅ Found Food Product: {} from restaurant: {}", productId, outletId);
            return foodProduct;  // ✅ Return as Product
        }

        log.warn("❌ Product not found: {} in outlet: {}", productId, outletId);
        return null;
    }

}