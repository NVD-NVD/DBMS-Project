package com.ute.dbms.webshop.entity;

public class UserForm {
    private String email;
    private String password;
    private String userName;
    private String phone;
    private String address;

    public UserForm(){}

    public UserForm(String email, String password, String userName, String phone, String address) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
    }

    public UserForm(String email, String userName, String phone, String address) {
        this.email = email;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
