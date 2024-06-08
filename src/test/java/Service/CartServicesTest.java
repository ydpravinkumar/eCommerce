package Service;

import com.example.supplychainback.Service.CartServices;
import com.example.supplychainback.model.Cart;
import com.example.supplychainback.model.LocalUser;
import com.example.supplychainback.model.Product;
import com.example.supplychainback.model.ProductQuantity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServicesTest {

    @Test
    void addToCart_existingCart() {
        CartServices cartServices = new CartServices();
        Long productId = 1L;
        Product product = new Product(productId, "ProductName");
        LocalUser localUser = new LocalUser();
        localUser.setUsername("abc");
        Cart cart = new Cart(new ArrayList<>(), localUser);
        ProductQuantity pq = new ProductQuantity(product, 1);
        cart.getProducts().add(pq);
        Cart updatedCart = cartServices.addToCart(productId, 1);
        assertNotNull(updatedCart, "Cart should not be null");
        assertEquals(1, updatedCart.getProducts().size(), "Cart should have atleast one type of product");
        ProductQuantity updatedPQ = updatedCart.getProducts().get(0);
        assertEquals(2, updatedPQ.getQuantity(), "Product quantity should be updated to 2");
        assertEquals(productId, updatedPQ.getProduct().getId(), "Product ID should match the added product");
    }

    @Test
    void addToCart_newCart() {
        CartServices cartServices = new CartServices();
        Long productId = 1L;
        Product product = new Product(productId, "ProductName");
        LocalUser localUser = new LocalUser();
        localUser.setUsername("abc");
        Cart newCart = cartServices.addToCart(productId, 1);
        assertNotNull(newCart, "Cart should not be null");
        assertEquals(1, newCart.getProducts().size(), "New cart should have exactly one product");
        ProductQuantity addedPQ = newCart.getProducts().get(0);
        assertEquals(productId, addedPQ.getProduct().getId(), "Product ID should match the added product");
        assertEquals(1, addedPQ.getQuantity(), "Product quantity should be exactly 1 as it's a new addition");
    }
}
