package com.ute.dbms.webshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INFOR")
public class UserInfo {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "NAME", nullable = false)
    private String userName;

    @Column(name = "PHONE", nullable = false)
    private Long phone;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    public UserInfo() {
    }
    public UserInfo(String userName, long phone, String address) {
        this.userName = userName;
        this.phone = phone;
        this.address = address;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        return "userName= " + userName +
                ",phone= " + phone +
                ",address=" + address;
    }
}
/**
 * CREATE TABLE USER_INFOR(
 ID_USER INT FOREIGN KEY (ID_USER) REFERENCES USERS(ID)  ON DELETE CASCADE  ON UPDATE CASCADE,
 name CHAR(30) NOT NULL,
 ADDRESS CHAR(30) CONSTRAINT diachi NOT NULL ,
 PHONE INT CONSTRAINT PHONE NOT NULL,
 PRIMARY KEY(ID_USER)
 )
 */