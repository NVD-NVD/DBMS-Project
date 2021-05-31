package com.ute.dbms.webshop.model;

public class BillForm {
    private String namePro;
    private int price;
    private int quantily;
    private int sum;

    public BillForm() {
    }

    public BillForm(String namePro, int price, int quantily, int sum) {
        this.namePro = namePro;
        this.price = price;
        this.quantily = quantily;
        this.sum = sum;
    }

    public String getNamePro() {
        return namePro;
    }

    public void setNamePro(String namePro) {
        this.namePro = namePro;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int price, int quantily) {
        this.sum = price * quantily;
    }

    @Override
    public String toString() {
        return "BillForm{" +
                "namePro='" + namePro + '\'' +
                ", price=" + price +
                ", quantily=" + quantily +
                ", sum=" + sum +
                '}';
    }
}
