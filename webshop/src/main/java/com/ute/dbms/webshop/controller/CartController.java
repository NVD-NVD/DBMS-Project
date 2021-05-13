package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.Product;
import com.ute.dbms.webshop.entity.User;
import com.ute.dbms.webshop.repository.ProductRepository;
import com.ute.dbms.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/product")
public class CartController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/cart/{id}")
    public String cart(@PathVariable("id") int id, Model model){
        if(productRepository.findById(id) ==null){
            System.out.println("\n\n null \n\n");
        }
        System.out.println("\n\n" + id);
        Product product = productRepository.findById(id);
        System.out.println("\n\n" + product.getId());
        System.out.println("\n\n" + product.getName());
        System.out.println("\n\n" + product.getImgurl());
        System.out.println("\n\n" + product.getSoLuong()    );
        System.out.println(" \n\n");
        model.addAttribute("product", product);
        return "cart";
    }
    @PostMapping(value = "/cart/add")
    public String addCart(@Valid Product product, HttpServletRequest request){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        
        return "redirect:/";
    }
}
