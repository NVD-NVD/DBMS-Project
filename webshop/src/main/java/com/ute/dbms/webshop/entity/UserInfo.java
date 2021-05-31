package com.ute.dbms.webshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER_INFO")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID")
    private User user;

    @Column(name = "NAME", nullable = false)
    private String userName;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    public UserInfo() {
    }
    public UserInfo(String userName, String phone, String address) {
        this.userName = userName;
        this.phone = phone;
        this.address = address;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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

    public String toString(){
        if(userName == null)
            userName = null;
        if(phone == null)
            phone = null;
        if(address == null)
            address = null;
        return "userName= " + userName +
                ",phone= " + phone +
                ",address=" + address;
    }
}
/**
 * CREATE TABLE user_info(
 id INT FOREIGN KEY (ID_USER) REFERENCES USERS(ID)  ON DELETE CASCADE  ON UPDATE CASCADE,
 name CHAR(30) NOT NULL,
 address CHAR(30) CONSTRAINT diachi NOT NULL ,
 phone INT CONSTRAINT PHONE NOT NULL,
 PRIMARY KEY(ID_USER)
 )
 */