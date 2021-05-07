package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.Product;
import com.ute.dbms.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/product")
public class CartController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/cart/{id}")
    public String cart(HttpServletRequest request, @PathVariable("id") int id, Model model){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("product", product);
        return "cart";
    }
}
