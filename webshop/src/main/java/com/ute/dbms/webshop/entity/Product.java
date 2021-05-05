package com.ute.dbms.webshop.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "context", nullable = false)
    private String context;
    @Column(name = "soluong", nullable = false)
    private int soLuong;
    @Column(name = "imgUrl")
    private String imgurl;

    public Product() {
    }

    public Product(int id, String name, int price, String context, int soLuong) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.context = context;
        this.soLuong = soLuong;
    }

    public Product(String name, int price, String context, int soLuong) {
        this.name = name;
        this.price = price;
        this.context = context;
        this.soLuong = soLuong;
    }

    public Product(String name, int price, String context, int soLuong, String imgurl) {
        this.name = name;
        this.price = price;
        this.context = context;
        this.soLuong = soLuong;
        this.imgurl = imgurl;
    }
    public Product(ProductBuilder productBuilder){
        this.name = productBuilder.name;
        this.price = productBuilder.price;
        this.context = productBuilder.context;
        this.soLuong = productBuilder.soLuong;
        this.imgurl = productBuilder.imgurl;
    }
    public static class ProductBuilder {
        private final String name;
        private int price;
        private String context;
        private int soLuong;
        private String imgurl;
        public ProductBuilder(String name) {
            this.name = name;
        }

        public ProductBuilder price(int price) {
            this.price = price;
            return this;
        }

        public ProductBuilder context(String context) {
            this.context = context;
            return this;
        }

        public ProductBuilder soLuong(int soLuong) {
            this.soLuong = soLuong;
            return this;
        }

        public ProductBuilder imgUrl(String imgurl) {
            this.imgurl = imgurl;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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