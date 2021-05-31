package com.ute.dbms.webshop.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private int id;
    private String name;
    private int price;
    private String context;
    private int quantily;
    private MultipartFile multipartFile;
    private String imgUrl;
    public ProductForm() {
    }

    public ProductForm(String name, int price, String context, int quantily, MultipartFile multipartFile) {
        this.name = name;
        this.price = price;
        this.context = context;
        this.quantily = quantily;
        this.multipartFile = multipartFile;
    }

    public ProductForm(int id, String name, int price, String context, int quantily, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.context = context;
        this.quantily = quantily;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
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

    @Override
    public String toString() {
        return "ProductForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", context='" + context + '\'' +
                ", quantily=" + quantily +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
