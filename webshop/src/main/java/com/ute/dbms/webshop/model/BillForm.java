package com.ute.dbms.webshop.model;

import com.ute.dbms.webshop.entity.Bill;
import com.ute.dbms.webshop.entity.Detail;
import com.ute.dbms.webshop.repository.BillRepository;
import com.ute.dbms.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BillForm {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillRepository billRepository;
    private String namePro;
    private int price;
    private int quantily;
    private int sum;

    public List<BillForm> getListBillForm(int id){
        Bill bi = billRepository.findById(id);
        if(bi == null)
            return null;
//        List<Detail> detailList = detailRepository.findAllById(bi.getId());
        List<BillForm> billFormList = null;
        List<Detail> detailList = billRepository.findAllDetailById(bi.getId());
        for(Detail detail : detailList){
            BillForm billForm = new BillForm();
            billForm.setNamePro(detail.getProduct().getName());
            billForm.setPrice(detail.getProduct().getPrice());
            billForm.setQuantily(detail.getQuantily());
            billForm.setSum(billForm.getPrice(), billForm.getQuantily());
            billFormList.add(billForm);
        }
        return billFormList;
    }

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
}
