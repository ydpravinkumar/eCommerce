package com.example.supplychainback.model.Dao;

import com.example.supplychainback.model.Product;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
/**
 * Product Repository to CRUD the database table for Product
 */
public interface Productdao extends CrudRepository<Product, Integer> {

    public List<Product> findAll();
    Optional<Product> findById(Long id);
}
