package com.ute.dbms.webshop.model;

public class OrderForm {
    private int idBill;
    private long idUser;
    private String date;
    private int sum;

    public OrderForm(){}

    public OrderForm(int idBill, long idUser, String date, int sum) {
        this.idBill = idBill;
        this.idUser = idUser;
        this.date = date;
        this.sum = sum;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "idBill=" + idBill +
                ", idUser=" + idUser +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                '}';
    }
}
