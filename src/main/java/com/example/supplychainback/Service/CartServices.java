package com.example.supplychainback.Service;

import com.example.supplychainback.model.Cart;
import com.example.supplychainback.model.Dao.Cartdao;
import com.example.supplychainback.model.Dao.Productdao;
import com.example.supplychainback.model.Dao.LocalUserDao;
import com.example.supplychainback.model.LocalUser;
import com.example.supplychainback.model.Product;
import com.example.supplychainback.model.ProductQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class to handle all the Cart related events like addToCart, display cart items
 */
@Service
public class CartServices {
    /**
     * Create the required instances for repository such as Cart, Product & LocalUser
     */
    @Autowired
    private Cartdao cartdao;
    @Autowired
    private Productdao productdao;
    @Autowired
    private LocalUserDao localuserdao;

    /**
     * Method to add products to cart
     * @param productId To identify the correct product to be added to cart
     * @param quantity The amount of product that needs to added to cart
     * @return update the cart repository with the products and save it in cart
     */
    public Cart addToCart(Long productId,int quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentuser = authentication.getName();
        Optional<Product> productopt = productdao.findById(productId);
        Optional<LocalUser> localUseropt = localuserdao.findByUsernameIgnoreCase(currentuser);
        if (productopt.isPresent() && localUseropt.isPresent()) {
        Product product = productopt.get();
        LocalUser localUser = localUseropt.get();
        Optional<Cart> existingCartOpt = cartdao.findByLocalUser(localUser);
        Cart cart;
        if(existingCartOpt.isPresent())
        {
            cart = existingCartOpt.get();
        }
        else{
            cart = new Cart(new ArrayList<>(),localUser);
        }
            List<ProductQuantity> productQuantities = cart.getProducts();
            ProductQuantity foundpq = null;
            for (ProductQuantity productQuantity : productQuantities) {
                if (productQuantity.getProduct().getId().equals(product.getId())) {
                    foundpq = productQuantity;
                    break;
                }
            }

            if (foundpq != null) {
                foundpq.setQuantity(foundpq.getQuantity() + quantity);
            } else {
                productQuantities.add(new ProductQuantity(product, quantity));
            }

            cart.setProducts(productQuantities);
            return cartdao.save(cart);
        }

        return null;
    }

    /**
     * All the items currently present in the cart display those items
     * @return list of product items
     */
    public List<ProductQuantity> getCartItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Optional<LocalUser> localUserOpt = localuserdao.findByUsernameIgnoreCase(currentUsername);

        if (localUserOpt.isPresent()) {
            LocalUser localUser = localUserOpt.get();
            Optional<Cart> cartOpt = cartdao.findByLocalUser(localUser);
            if (cartOpt.isPresent()) {
                return cartOpt.get().getProducts();
            }
        }
        return new ArrayList<>();
    }

    public void clearCart(Long Id) {
        cartdao.deleteAllById(Id);
    }

}