package com.ute.dbms.webshop.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "CONTENT")
    private String context;
    @Column(name = "QUANTILY")
    private int quantily;
    @Column(name = "IMGURL")
    private String imgurl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<Detail> detail;
    public Product() {
    }

    public Product(int id, String name, int price, String context, int quantily) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.context = context;
        this.quantily = quantily;
    }

    public Product(int id, String name, int price, String context, int quantily, String imgurl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.context = context;
        this.quantily = quantily;
        this.imgurl = imgurl;
    }

    public Product(String name, int price, String context, int quantily) {
        this.name = name;
        this.price = price;
        this.context = context;
        this.quantily = quantily;
    }

    public Product(String name, int price, String context, int quantily, String imgurl) {
        this.name = name;
        this.price = price;
        this.context = context;
        this.quantily = quantily;
        this.imgurl = imgurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuantily() {
        return quantily;
    }

    public void setQuantily(int quantily) {
        this.quantily = quantily;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", context='" + context + '\'' +
                ", quantily=" + quantily +
                ", imgurl='" + imgurl + '\'' +
                ", detail=" + detail +
                '}';
    }
}
/*
* CREATE TABLE PRODUCT(
	ID INTEGER CONSTRAINT MaSP PRIMARY KEY NOT NULL,
	NAME CHAR(12) CONSTRAINT TenSP NOT NULL,
	PRICE INTEGER CONSTRAINT GIA CHECK(PRICE>=0) NOT NULL,
	QUANTILY INTEGER CONSTRAINT SLTONKHO CHECK(QUANTILY_WH>=0) NOT NULL
	)
*/