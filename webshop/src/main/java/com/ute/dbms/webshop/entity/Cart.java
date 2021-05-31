package com.ute.dbms.webshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;


    @Column(name = "ID_USER")
    private long userID;

    @Column(name = "ID_PRODUCT")
    private int productID;

    @Column
    private int quantity;

    public Cart() {
    }

    public Cart(Long userID, int productID, int quantity) {
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{"
                + "id: " + id
                + " userID: " + userID
                + " productID: " + productID
                + " quantily: " + quantity
                + "}";
    }
}
