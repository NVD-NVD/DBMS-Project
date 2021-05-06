package com.ute.dbms.webshop.entity;

import java.util.Optional;

public class Cart {
    private Optional<Product> product;
    private int quantity;

    public Cart() {
    }

    public Cart(Optional<Product> product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Optional<Product> getProduct() {
        return product;
    }

    public void setProduct(Optional<Product> product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
