package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.*;
import com.ute.dbms.webshop.model.BillForm;
import com.ute.dbms.webshop.model.CartForm;
import com.ute.dbms.webshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private CartRepository cartRepository;
    @GetMapping("/cart")
    public String userCart(Model model, HttpServletRequest request) {
        try {
            User user = userRepository.findByEmail(request.getUserPrincipal().getName());
            List<Cart> cartList = cartRepository.findAllByUserID(user.getId());
            List<CartForm> cartFormList = null;
            int sum = 0;
            for (Cart cart : cartList) {
                Product product = productRepository.findById(cart.getProductID());
                CartForm cartFrom = new CartForm(product.getId(),
                        product.getName(), product.getImgurl(), product.getPrice(),
                        cart.getQuantity(), product.getPrice() * cart.getQuantity());
                cartFormList.add(cartFrom);
                sum += product.getPrice() * cart.getQuantity();
                System.out.println(cart.toString());
            }
            model.addAttribute("cartFromList", cartFormList);
            model.addAttribute("sum", sum);
        }
        catch (NullPointerException e){
        }
        return "user-cart";
    }
    @PostMapping(value = "/cart/add/{id}")
    public String addCart(@PathVariable("id") int id, @Valid Product product, HttpServletRequest request){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        Cart cart = new Cart(user.getId(), id, product.getQuantily());
        System.out.println("\n" + product.getId());
        cartRepository.save(cart);
        return "redirect:/";
    }
    @GetMapping("/cart/remove/{id}")
    public String removeCart(@PathVariable("id") int id, HttpServletRequest request){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        Cart cart = cartRepository.findByUserIDAndProductID(user.getId(), id);
        cartRepository.delete(cart);
        return "redirect:/user/cart";
    }
    @PostMapping(value = "/order")
    public String orderCart(HttpServletRequest request){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        List<Cart> cartList = cartRepository.findAllByUserID(user.getId());
        Bill bill = new Bill();
        bill.setUser(user);
        int sum = 0;
        List<Detail> detailList = null;
        for(Cart cart : cartList){
            Detail detail = new Detail();
            Product product = productRepository.findById(cart.getProductID());
            detail.setProduct(product);
            detail.setQuantily(cart.getQuantity());
            sum += product.getPrice();
            detail.setBill(bill);
            detailList.add(detail);
        }
        cartRepository.deleteAll(cartList);
        bill.setDetail(detailList);
        bill.setSum(sum);
        billRepository.save(bill);
        detailRepository.saveAll(detailList);
        return "index";
    }
    @GetMapping("/checkorder")
    public String CheckOrder(String idBill){
        return "checkorder";
    }
    @PostMapping("/detailorder")
    public String DetailOrder(HttpServletRequest request, @Valid Bill bill, Model model){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        UserForm userForm = new UserForm(
                user.getEmail(),user.getUserInfo().getUserName(),
                user.getUserInfo().getPhone(),user.getUserInfo().getAddress());
        List<BillForm> billFormList = new BillForm().getListBillForm(bill.getId());
        Bill bi = billRepository.findById(bill.getId());
        model.addAttribute("billFormList", billFormList);
        model.addAttribute("sumBill", bi.getSum());
        model.addAttribute("status",bi.isStatus());
        model.addAttribute("userInfo", userForm);
        return "detailorder";
    }
}
