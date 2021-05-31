package com.ute.dbms.webshop.model;

import com.ute.dbms.webshop.entity.Cart;
import com.ute.dbms.webshop.entity.Product;
import com.ute.dbms.webshop.entity.User;
import com.ute.dbms.webshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    public final List<CartForm> createCartForm(String email, int sum){
        User user = userRepository.findByEmail(email);
        List<Cart> cartList = cartRepository.findAllByUserID(user.getId());
        List<CartForm> cartFormList = new ArrayList<CartForm>();
        for (Cart cart : cartList) {
            Product product = productRepository.findById(cart.getProductID());
            CartForm cartFrom = new CartForm(cart.getProductID(),
                    product.getName(), product.getImgurl(), product.getPrice(),
                    cart.getQuantity(), product.getPrice() * cart.getQuantity());

            cartFormList.add(cartFrom);
            sum += product.getPrice() * cart.getQuantity();
        }
        return cartFormList;
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

    @Override
    public String toString() {
        return "CartForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", quantily=" + quantily +
                ", money=" + money +
                '}';
    }
}
