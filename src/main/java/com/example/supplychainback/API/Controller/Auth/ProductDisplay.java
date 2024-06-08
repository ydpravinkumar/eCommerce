package com.example.supplychainback.API.Controller.Auth;

import com.example.supplychainback.model.Dao.Productdao;
import com.example.supplychainback.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
/**
 * Displays all the products in the product table of the database on the shop page of the application
 */
public class ProductDisplay {
    @Autowired
    private Productdao productdao;

    @GetMapping("/listall")
    public List<Product> getAllProducts() {
        return productdao.findAll();
    }
}
