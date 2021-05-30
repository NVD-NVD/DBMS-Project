package com.ute.dbms.webshop.model;

public class CartForm {
    private int id;
    private String name;
    private String img;
    private int price;
    private int quantily;
    private int money;
    public CartForm(){}

    public CartForm(int id, String name, String img, int price, int quantily, int money) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.quantily = quantily;
        this.money = money;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantily() {
        return quantily;
    }

    public void setQuantily(int quantily) {
        this.quantily = quantily;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
