package com.ute.dbms.webshop.entity;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private int id;
    private String name;
    private int price;
    private String context;
    private int soLuong;
    private MultipartFile imgurl;

    public ProductForm(){
    }
    public ProductForm(ProductFormBuilder productFormBuilder) {
        this.name = productFormBuilder.name;
        this.price = productFormBuilder.price;
        this.context = productFormBuilder.context;
        this.soLuong = productFormBuilder.soLuong;
        this.imgurl = productFormBuilder.imgurl;
    }
    public static class ProductFormBuilder{
        private final String name;
        private int price;
        private String context;
        private int soLuong;
        private MultipartFile imgurl;
        public ProductFormBuilder(String name) {
            this.name = name;
        }

        public ProductFormBuilder price(int price) {
            this.price = price;
            return this;
        }

        public ProductFormBuilder context(String context) {
            this.context = context;
            return this;
        }

        public ProductFormBuilder soLuong(int soLuong) {
            this.soLuong = soLuong;
            return this;
        }

        public ProductFormBuilder imgUrl(MultipartFile imgurl) {
            this.imgurl = imgurl;
            return this;
        }

        public ProductForm build() {
            return new ProductForm(this);
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

    public MultipartFile getImgurl() {
        return imgurl;
    }

    public void setImgurl(MultipartFile imgurl) {
        this.imgurl = imgurl;
    }
}
