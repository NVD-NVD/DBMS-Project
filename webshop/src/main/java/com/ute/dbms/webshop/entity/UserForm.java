package com.ute.dbms.webshop.entity;

import javax.persistence.Column;

public class UserForm {
    private String email;
    private String password;
    private String userName;
    private Long phone;
    private String address;

    public UserForm(){}

    public UserForm(String email, String password, String userName, Long phone, String address) {
        this.email = email;
        this.password = password;
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
