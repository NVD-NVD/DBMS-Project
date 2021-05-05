package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.Product;
import com.ute.dbms.webshop.entity.ProductForm;
import com.ute.dbms.webshop.entity.FileUploadUtil;
import com.ute.dbms.webshop.repository.ProductRepository;
import com.ute.dbms.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping(value = "/")
    public String home1(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping("/cart")
    public String about() {
        return "/cart";
    }
    @GetMapping("/login")
    public String login() {
        return "/sign-in";
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
//    @GetMapping("/loginTrue")
//    public String getLogin(Model model, Principal principal) {
//        String userName = principal.getName() ;
//        model.addAttribute("userModel", principal);
//
//        return "redirect:/index";
//    }
//    @GetMapping("/loginError")
//    public String loginerror(HttpServletRequest request) {
//        System.out.println(request.toString());
//        return "redirect:/login?error";
//    }
//    @GetMapping("/loginAcce")
//    public String getLogin(HttpServletRequest request) {
//        if (request.isUserInRole("ROLE_ADMIN")) {
//            return "redirect:/admin";
//        }
//        return "redirect:/user";
//    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
    @GetMapping(value = "/signup")
    public String  signup(){
        return "sign-up";
    }

    @GetMapping("/admin/product/list")
    public String adminPage(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        for(Product product : productList){
            System.out.println(product.toString());
        }
        return "/admin";
    }
    @GetMapping("/admin/products/add")
    public String addProduct(HttpServletRequest request, ProductForm productForm){
        if(request.isUserInRole("ADMIN")){
            return "/add-product";
        }
        return "/index";
    }
    @PostMapping("/admin/products/save")
    public String AddProduct(@Valid ProductForm productForm, HttpServletRequest request) throws IOException{

        if (request.isUserInRole("ADMIN")) {
            Product product1 = new Product.ProductBuilder(productForm.getName())
                    .price(productForm.getPrice())
                    .context(productForm.getContext())
                    .soLuong(productForm.getSoLuong()).build();
            MultipartFile multipartFile = productForm.getImgurl();
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String currentDirectory = System.getProperty("user.dir");
            FileUploadUtil.saveFile(currentDirectory + "/src/main/resources/static/images/products/", fileName, multipartFile);
            String fileUrl = "images/products/" + fileName;
            product1.setImgurl(fileUrl);
            productRepository.save(product1);
            return "redirect:/admin/product/list";
        }
        return "/index";
    }
}
