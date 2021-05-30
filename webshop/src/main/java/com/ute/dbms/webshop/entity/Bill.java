package com.ute.dbms.webshop.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "BILL")
public class Bill {
    @Id
    @GeneratedValue
    @Column(name = "ID_BILL")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @Column(name = "DATE", nullable = false)
    private Date date;

    @Column(name = "SUM", nullable = false)
    private long sum;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private List<Detail> detail;

    @Column(name = "STATUS")
    private boolean status;

    public Bill() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }
}
/*CREATE TABLE BILL(
	IDBILL INTEGER CONSTRAINT MAHD UNIQUE NOT NULL,
	IDUSER INTEGER CONSTRAINT IDKH FOREIGN KEY(IDUSER) REFERENCES USERS(ID) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	DATE Smalldatetime CONSTRAINT NGAY NOT NULL,
	SUM Money CONSTRAINT TONG CHECK(SUM>=0),
	STATUS BOOL,
	PRIMARY KEY (IDBILL)
	)*/