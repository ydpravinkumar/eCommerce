package com.example.supplychainback.API.Controller.Auth;

import com.example.supplychainback.Service.CartServices;
import com.example.supplychainback.model.Product;
import com.example.supplychainback.model.ProductQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.supplychainback.model.Cart;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
/**
 * The Cart Controller class is responsible for adding the products to the cart and displaying the products in the cart
 * It uses the cart service addToCart method and getCartItems method
 */
public class CartController {
    @Autowired
    private CartServices cartServices;

    @PostMapping("/addtocart/{productId}/{quantity}")
    public ResponseEntity<?> addToCart(@PathVariable(name = "productId") Long productId, @PathVariable(name = "quantity") int quantity) {
        try {
            Cart cart = cartServices.addToCart(productId, quantity);
            if (cart == null) {
                return ResponseEntity.badRequest().body("Product or user not found");
            }
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/getcart")
    public ResponseEntity<?> getCartItems() {
        List<ProductQuantity> items = cartServices.getCartItems();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

}

