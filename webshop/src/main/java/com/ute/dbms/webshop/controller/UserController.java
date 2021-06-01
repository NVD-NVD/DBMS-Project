package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.*;
import com.ute.dbms.webshop.model.BillForm;
import com.ute.dbms.webshop.model.CartForm;
import com.ute.dbms.webshop.model.UserForm;
import com.ute.dbms.webshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
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
            int sum = 0;
            List<CartForm> cartFormList = new ArrayList<CartForm>();
            List<Cart> cartList = cartRepository.findAllByUserID(user.getId());
            for (Cart cart : cartList) {
                Product product = productRepository.findById(cart.getProductID());
                CartForm cartFrom = new CartForm(cart.getProductID(),
                        product.getName(), product.getImgurl(), product.getPrice(),
                        cart.getQuantity(), product.getPrice() * cart.getQuantity());
                cartFormList.add(cartFrom);
                sum += product.getPrice() * cart.getQuantity();
            }
            model.addAttribute("cartFormList", cartFormList);
            model.addAttribute("sum", sum);
            for(CartForm cartForm : cartFormList)
                System.out.println(cartForm.toString());
        }
        catch (NullPointerException e){
        }
        return "user-cart";
    }
    @PostMapping(value = "/cart/add/{id}")
    public String addCart(@PathVariable("id") int id, @Valid Product product, HttpServletRequest request){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        Cart cart = cartRepository.findByUserIDAndProductID(user.getId(), id);
        if(cart == null) {
            cart = new Cart(user.getId(), id, product.getQuantily());
        }
        else{
            cart.setQuantity(cart.getQuantity() + product.getQuantily());
        }

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
    @GetMapping(value = "/cart/order")
    public String orderCart(HttpServletRequest request){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        List<Cart> cartList = cartRepository.findAllByUserID(user.getId());
        Bill bill = new Bill();
        bill.setUser(user);
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        System.out.println(date);
        bill.setDate(date);
        int sum = 0;
        List<Detail> detailList = new ArrayList<Detail>();
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
        //detailRepository.saveAll(detailList);
        return "redirect:/";
    }
    @GetMapping("/checkorder")
    public String CheckOrder(Model model){
        Bill bill = new Bill();
        model.addAttribute("bill", bill);
        return "user-checkorder";
    }
    @GetMapping("/detailorder")
    public String DetailOrder(HttpServletRequest request, @RequestParam("id") int id, Model model){
        try{
            User user = userRepository.findByEmail(request.getUserPrincipal().getName());
            UserForm userForm = new UserForm(
                    user.getEmail(),user.getUserInfo().getUserName(),
                    user.getUserInfo().getPhone(),user.getUserInfo().getAddress());
            Bill bill = billRepository.findById(id);
            List<BillForm> billFormList = new ArrayList<BillForm>();
            List<Detail> detailList = detailRepository.findAllById(id);
            for(Detail detail : detailList){
                BillForm billForm = new BillForm();
                billForm.setNamePro(detail.getProduct().getName());
                billForm.setPrice(detail.getProduct().getPrice());
                billForm.setQuantily(detail.getQuantily());
                billForm.setSum(detail.getProduct().getPrice(), detail.getQuantily());
                billFormList.add(billForm);
            }
            for(BillForm billForm : billFormList)
                System.out.println(billForm.toString());
            model.addAttribute("billFormList", billFormList);
            model.addAttribute("bill",bill);
            model.addAttribute("sumBill", bill.getSum());
            model.addAttribute("status",bill.isStatus());
            model.addAttribute("userForm", userForm);
        }
        catch (NullPointerException e){
            return "user-checkorder";
        }
        return "detailorder";
    }
}
