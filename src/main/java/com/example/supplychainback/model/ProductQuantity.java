package com.example.supplychainback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
/**
 * Created the entity for Product Quantity
 */
@Entity
public class ProductQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quantityid;

    @ManyToOne
    private Product product;
    private int quantity;

    public ProductQuantity() {

    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public ProductQuantity(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
