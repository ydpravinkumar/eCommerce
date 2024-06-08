package com.example.supplychainback.model;
import jakarta.persistence.*;

import java.util.List;

/**
 * Created the entity for Cart
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartID;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "quantity",
            joinColumns = @JoinColumn(name = "cartID"),
            inverseJoinColumns = @JoinColumn(name = "quantityid")
    )
    private List<ProductQuantity> products;

    @ManyToOne
    private LocalUser localUser;


    public Cart() {

    }

    public LocalUser getLocalUser() {
        return localUser;
    }

    public void setLocalUser(LocalUser localUser) {
        this.localUser = localUser;
    }



    public Cart(List<ProductQuantity> products, LocalUser localUser) {
        this.products = products;
        this.localUser = localUser;
    }


    public List<ProductQuantity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductQuantity> products) {
        this.products = products;
    }
}
