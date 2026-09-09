package com.tw.joi.delivery.seedData;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.domain.FoodProduct;
import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.domain.GroceryStore;
import com.tw.joi.delivery.domain.Outlet;
import com.tw.joi.delivery.domain.Product;
import com.tw.joi.delivery.domain.Restaurant;
import com.tw.joi.delivery.domain.User;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SeedData {
	
	// ========== GROCERY STORES ==========
    public static GroceryStore store101 = SeedData.createStore("Fresh Picks", "store101");
    public static GroceryStore store102 = SeedData.createStore("Natural Choice", "store102");
    
    // ========== RESTAURANTS ==========
    public static Restaurant restaurant201 = SeedData.createRestaurant("Spice Kitchen", "restaurant201", "Indian", 30);
    public static Restaurant restaurant202 = SeedData.createRestaurant("Dragon Palace", "restaurant202", "Chinese", 25);
    
    // ========== USERS ==========
    public static User user101= SeedData.createUser("user101", "John", "Doe");
    public static User user102 = SeedData.createUser("user102", "Rachel", "Zane");
    
    // ========== CARTS ==========
    public static Map<String, Cart> cartForUsers = Map.of(
        "user101", createCartForUser("user101", "John", "Doe", "cart101"),
        "user102", createCartForUser("user102", "Rachel", "Zane", "cart102"));
    
    // ========== GROCERY PRODUCTS ==========
    public static List<GroceryProduct> groceryProducts =
        Arrays.asList(createGroceryProduct("Wheat Bread", "gp101", store101, 3.50, 500, 20, 5, 30),
                      createGroceryProduct("Spinach", "gp102", store101, 2.50, 200, 15, 10, 25),
                      createGroceryProduct("Crackers", "gp103", store101, 4.50, 400, 60, 3, 20));

    // ========== FOOD PRODUCTS ==========
    public static List<FoodProduct> foodProducts = Arrays.asList(
            createFoodProduct("Butter Chicken", "fp101", restaurant201, 12.99, "North Indian", 20, 15, 5),
            createFoodProduct("Paneer Tikka", "fp102", restaurant201, 10.50, "North Indian", 15, 20, 5),
            createFoodProduct("Chow Mein", "fp103", restaurant202, 8.99, "Chinese", 18, 25, 8),
            createFoodProduct("Kung Pao Chicken", "fp104", restaurant202, 11.50, "Chinese", 20, 18, 6)
        );
    // ========== ALL USERS ==========
    public static List<User> users = Arrays.asList(user101, user102);

    public static Cart createCartForUser(String userId, String firstName, String lastName,
                                         String cartId, Outlet outlet) {
    	User user = SeedData.createUser(userId, firstName, lastName);
        return Cart.builder()
            .cartId(cartId)
            .outlet(outlet)
            .user(user)
            .build();
    }

    public static GroceryStore createStore(String outletName, String storeId) {
        return GroceryStore.builder()
            .name(outletName)
            .outletId(storeId)
            .build();
    }
    
	public static Restaurant createRestaurant(String name, String restaurantId,
											  String cuisineCategory, int deliveryTime) {
		return Restaurant.builder()
			.name(name)
			.outletId(restaurantId)
			.description("Delivering fresh " + cuisineCategory + " cuisine")
			.cuisineCategory(cuisineCategory)
			.deliveryTime(deliveryTime)
			.build();
	}

    public static User createUser(String userId, String firstName, String lastName) {
        return User.builder()
            .userId(userId)
            .firstName(firstName)
            .lastName(lastName)
            .email(firstName + "." + lastName + "@gmail.com")
            .phoneNumber(String.valueOf(SeedData.getRandomNumberUsingNextInt(100000000, 900000000)))
            .build();
    }

    private static GroceryProduct createGroceryProduct(String productName, String productId,
            										   GroceryStore store, double sellingPrice,
            										   int weight, int expiryDays,
            										   int threshold, int availableStock) {
        return GroceryProduct.builder()
            .productName(productName)
            .productId(productId)
            .mrp(BigDecimal.valueOf(sellingPrice * 1.2))
            .sellingPrice(BigDecimal.valueOf(sellingPrice))
            .discount(BigDecimal.valueOf(15))
            .weight(BigDecimal.valueOf(weight))
            .expiryDate(expiryDays)
            .threshold(threshold)
            .availableStock(availableStock)
            .store(store)
            .build();
    }
    
    private static FoodProduct createFoodProduct(String productName, String productId,
            									 Restaurant restaurant, double sellingPrice,
            									 String cuisineType, int prepTime,
            									 int threshold, int availableStock) {
    	return FoodProduct.builder()
    			.productName(productName)
				.productId(productId)
				.mrp(BigDecimal.valueOf(sellingPrice * 1.15))  
				.sellingPrice(BigDecimal.valueOf(sellingPrice))
				.discount(BigDecimal.valueOf(10))  
				.cuisineType(cuisineType)
				.preparationTime(prepTime)
				.isVegetarian(productName.contains("Paneer") || productName.contains("Chow"))
				.rating(BigDecimal.valueOf(4.5))
				.threshold(threshold)  // 
				.availableStock(availableStock)
				.restaurant(restaurant)
				.build();
    }
    
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}