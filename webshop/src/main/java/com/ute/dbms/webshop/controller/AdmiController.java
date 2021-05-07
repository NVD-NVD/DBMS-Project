package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.FileUploadUtil;
import com.ute.dbms.webshop.entity.Product;
import com.ute.dbms.webshop.entity.ProductForm;
import com.ute.dbms.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdmiController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/list")
    public String adminPage(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        for(Product product : productList){
            System.out.println(product.toString());
        }
        return "/admin";
    }
    @GetMapping("/products/add")
    public String addProduct(HttpServletRequest request, ProductForm productForm){
        if(request.isUserInRole("ADMIN")){
            return "/add-product";
        }
        return "/index";
    }
    @PostMapping("/products/save")
    public String AddProduct(@Valid ProductForm productForm, HttpServletRequest request) throws IOException {

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
