package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.*;
import com.ute.dbms.webshop.repository.ProductRepository;
import com.ute.dbms.webshop.repository.RoleRepository;
import com.ute.dbms.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping()
    public String adminPage(){
        return "/admin";
    }
    @GetMapping("/product")
    public String adminPage(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "/admin-sanpham";
    }
    @GetMapping("/products/add")
    public String addProduct(HttpServletRequest request, Product product, MultipartFile multipartFile){
        if(request.isUserInRole("ADMIN")){
            return "/add-product";
        }
        return "/index";
    }
    @PostMapping("/products/save")
    public String AddProduct(@Valid Product product, MultipartFile multipartFile, HttpServletRequest request) throws IOException {

        if (request.isUserInRole("ADMIN")) {
            Product product1 = new Product(product.getName()
                    ,product.getPrice()
                    ,product.getContext()
                    ,product.getSoLuong());
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String currentDirectory = System.getProperty("user.dir");
            FileUploadUtil.saveFile(currentDirectory + "/src/main/resources/static/images/products/", fileName, multipartFile);
            String fileUrl = "images/products/" + fileName;
            product1.setImgurl(fileUrl);
            productRepository.save(product1);
            return "redirect:/admin/product/sanpham";
        }
        return "/index";
    }

    @GetMapping("/nhanvien")
    public String nhanvien(Model model){
        List<User> list = userRepository.findAll();
        for (User user : list) {
            for (Role role : user.getRoles()) {
                System.out.println(role);
                if(role.equals("ROLE_STAFF"))
                    list.remove(user);
            }
        }
        model.addAttribute("accounts", list);

        return "/admin-nv";
    }
    @GetMapping("/nhanvien/add")
    public String ThemNhanVien(HttpServletRequest request, Model model){
        if(request.isUserInRole("ADMIN")){
            model.addAttribute("userForm", new UserForm());
            return "/add-nv";
        }
        return "/admin-nv";
    }
    @PostMapping("/nhanvien/register")
    public String register(@Valid UserForm userForm){
        System.out.println(userForm.getEmail());
        if(userRepository.findByEmail(userForm.getEmail()) == null){
            User oth = new User();
            oth.setEmail(userForm.getEmail());
            oth.setPassword(passwordEncoder.encode(userForm.getPassword()));
            userRepository.save(oth);
            UserInfo userInfo = new UserInfo(userForm.getUserName(), userForm.getPhone(), userForm.getAddress());
            oth.setUserInfo(userInfo);
            userInfo.setUser(oth);
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("ROLE_STAFF"));
            oth.setRoles(roles);
            userRepository.save(oth);
            return "redirect:/admin-nv";
        }
        return "redirect:/signup?error";
    }
}
