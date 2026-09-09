package com.tw.joi.delivery.service;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.domain.FoodProduct;
import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.domain.Product;
import com.tw.joi.delivery.domain.User;
import com.tw.joi.delivery.dto.request.AddProductRequest;
import com.tw.joi.delivery.dto.response.CartProductInfo;
import com.tw.joi.delivery.seedData.SeedData;
import java.math.BigDecimal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final Map<String, Cart> userCarts = SeedData.cartForUsers;
    private final UserService userService;
    private final ProductService productService;

   
    public CartProductInfo addProductToCartForUser(AddProductRequest addProductRequest) {
        log.info("Adding product to cart - userId: {}, productId: {}",
                 addProductRequest.getUserId(), addProductRequest.getProductId());

        User user = userService.fetchUserById(addProductRequest.getUserId());
        Cart cart = fetchCartForUser(user);
        
        // getProduct() now returns generic Product (can be Grocery or Food)
        Product product = productService.getProduct(addProductRequest.getProductId(),
                                                    addProductRequest.getOutletId());

        // Same add logic - works for both
        cart.getProducts().add(product);
        
        // Get selling price polymorphically
        BigDecimal sellingPrice = getSellingPrice(product);
        
        log.info("Product {} added to cart", product.getProductName());
        return new CartProductInfo(cart, product, sellingPrice);
    }

    public Cart getCartForUser(String userId) {
        User user = userService.fetchUserById(userId);
        return fetchCartForUser(user);
    }

    private Cart fetchCartForUser(User user) {
        return userCarts.get(user.getUserId());
    }

    private BigDecimal getSellingPrice(Product product) {
        if (product instanceof GroceryProduct) {
            return ((GroceryProduct) product).getSellingPrice();
        } else if (product instanceof FoodProduct) {
            return ((FoodProduct) product).getSellingPrice();
        }
        return BigDecimal.ZERO;
    }

}