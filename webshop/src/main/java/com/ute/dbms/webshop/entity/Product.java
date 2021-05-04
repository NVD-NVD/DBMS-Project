package com.ute.dbms.webshop.entity;

import javax.persistence.*;
import java.security.PrivateKey;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private int productID;
    @Column(name = "name", nullable = false, unique = true)
    private String productName;
    @Column(name = "price", nullable = false, unique = true)
    private int price;
    @Column(name = "context", nullable = false, unique = true)
    private String context;
    @Column(name = "soluong", nullable = false, unique = true)
    private String soLuong;
    public Product() {
    }

    public Product(int productID, String productName, int price, String context) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.context = context;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
/*
* CREATE TABLE WAREHOUSE(
	GOODSID INTEGER CONSTRAINT MaSP PRIMARY KEY NOT NULL,
	GOODSNAME CHAR(12) CONSTRAINT TenSP NOT NULL,
	PRICE INTEGER CONSTRAINT GIA CHECK(PRICE>=0) NOT NULL,
	QUANTILY_WH INTEGER CONSTRAINT SLTONKHO CHECK(QUANTILY_WH>=0) NOT NULL
	)
*/