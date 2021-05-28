package com.ute.dbms.webshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "DETAIL")
public class Detail {
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "QUANTILY")
    private int quantily;

    public Detail() {
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantily() {
        return quantily;
    }

    public void setQuantily(int quantily) {
        this.quantily = quantily;
    }
}
/*CREATE TABLE DETAIL(
	IDBILL INTEGER REFERENCES BILL(IDBILL) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	GOODSID INTEGER REFERENCES WAREHOUSE(GOODSID) ON UPDATE CASCADE NOT NULL ,
	QUANTILY INTEGER CONSTRAINT SL CHECK(QUANTILY>=0) NOT NULL,
	PRIMARY KEY (IDBILL,GOODSID)
	)*/