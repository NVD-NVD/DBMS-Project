package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.*;
import com.ute.dbms.webshop.model.BillForm;
import com.ute.dbms.webshop.repository.BillRepository;
import com.ute.dbms.webshop.repository.CartRepository;
import com.ute.dbms.webshop.repository.DetailRepository;
import com.ute.dbms.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private CartRepository cartRepository;
    @PostMapping(value = "/cart/add")
    public String addCart(@Valid Product product, HttpServletRequest request){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        Cart cart = new Cart(user.getId(), product.getId(), product.getQuantily());

        cartRepository.save(cart);
        return "index";
    }
    @PostMapping(value = "/order")
    public String orderCart(HttpServletRequest request){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        Cart cart = cartRepository.findByUserID(user.getId());

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
