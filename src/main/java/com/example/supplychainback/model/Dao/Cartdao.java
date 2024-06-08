package com.example.supplychainback.model.Dao;

import com.example.supplychainback.model.LocalUser;
import com.example.supplychainback.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.supplychainback.model.Cart;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * Cart Repository to CRUD the database table for cart
 */
@Repository
public interface Cartdao extends CrudRepository<Cart, Integer> {

     Optional<Cart> findByLocalUser(LocalUser localUser);
     @Transactional
     @Modifying
     @Query("DELETE FROM Cart c WHERE c.localUser.id = :id")
    Optional<Cart> deleteAllById(Long id);
}
