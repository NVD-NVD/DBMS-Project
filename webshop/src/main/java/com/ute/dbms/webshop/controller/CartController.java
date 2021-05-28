package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.Product;
import com.ute.dbms.webshop.entity.User;
import com.ute.dbms.webshop.repository.ProductRepository;
import com.ute.dbms.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/{id}")
    public String cart(@PathVariable("id") int id, Model model){
        Product product = productRepository.findById(id);
        if(product == null){
            System.out.println("\n\n null \n\n");
        }
        model.addAttribute("product", product);
        return "product";
    }
    @PostMapping(value = "/add")
    public String addCart(@Valid Product product, HttpServletRequest request){
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());

        return "redirect:/";
    }
}
