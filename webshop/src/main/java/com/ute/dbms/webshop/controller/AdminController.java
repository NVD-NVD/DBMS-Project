package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.Bill;
import com.ute.dbms.webshop.entity.Detail;
import com.ute.dbms.webshop.entity.Product;
import com.ute.dbms.webshop.entity.User;
import com.ute.dbms.webshop.model.*;
import com.ute.dbms.webshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
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
    private BillRepository billRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping()
    public String adminPage(){
        return "/admin";
    }
    @GetMapping("/product")
    public String adminProducts(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "admin-sanpham";
    }

    @GetMapping("/product/add")
    public String addProduct(Model model){
        ProductForm productForm = new ProductForm();

        model.addAttribute("product", productForm);
        return "add-product";
    }

    @PostMapping("/product/save")
    public String saveProduct(@Valid ProductForm productForm, HttpServletRequest request) throws IOException {

        if (request.isUserInRole("ADMIN")) {
            Product product = new Product(productForm.getName()
                    ,productForm.getPrice()
                    ,productForm.getContext()
                    ,productForm.getQuantily());
            String fileName = StringUtils.cleanPath(productForm.getMultipartFile().getOriginalFilename());
            String currentDirectory = System.getProperty("user.dir");
            FileUploadUtil.saveFile(currentDirectory + "/src/main/resources/static/images/products/", fileName, productForm.getMultipartFile());
            String fileUrl = "images/products/" + fileName;
            product.setImgurl(fileUrl);
            productRepository.save(product);
            return "redirect:/admin/product";
        }
        return "/index";
    }
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        Product product = productRepository.findById(id);
        ProductForm productForm = new ProductForm(
                product.getId(), product.getName(), product.getPrice(), product.getContext(),
                product.getQuantily(), product.getImgurl());
        model.addAttribute("productForm", productForm);
        return "edit-product";
    }
    @PostMapping("/product/edit/save")
    public String editSaveProduct(ProductForm productForm) throws IOException{
        Product product = productRepository.findById(productForm.getId());
        product.setQuantily(product.getQuantily());
        if(product.getQuantily() < productForm.getQuantily()){
            product.setQuantily(productForm.getQuantily());
            productRepository.save(product);
        }
        return "redirect:/admin/product";
    }
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model){
        Product product = productRepository.findById(id);
        ProductForm productForm = new ProductForm(
                product.getId(), product.getName(), product.getPrice(), product.getContext(),
                product.getQuantily(), product.getImgurl());
        model.addAttribute("productForm", productForm);
        model.addAttribute("id", id);
        return "delete-product";
    }
    @PostMapping("/product/delete/save")
    public String deleteSaveProduct(ProductForm productForm) throws IOException{
        Product product = productRepository.findById(productForm.getId());
        productRepository.delete(product);
        return "redirect:/admin/product";
    }
    @GetMapping("/order")
    public String listOrder(Model model){
        List<Bill> billList = billRepository.findAllByStatus(false);
        List<OrderForm> orderFormList = new ArrayList<OrderForm>();
        for(Bill bill : billList){
            OrderForm orderForm = new OrderForm(
                    bill.getId(),bill.getUser().getId(),
                    bill.getDate().toString(), bill.getSum());
            orderFormList.add(orderForm);
            System.out.println(orderForm.toString());
        }
        for(OrderForm orderForm : orderFormList)
            System.out.println(orderForm.toString());
        model.addAttribute("orderFormList", orderFormList);
        return "admin-donhang";
    }
    @GetMapping("/order/detail/{id}")
    public String detailOrder(@PathVariable("id") int id, HttpServletRequest request, Model model){
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
        model.addAttribute("idBil",id);
        model.addAttribute("sumBill", bill.getSum());
        model.addAttribute("userForm", userForm);

        return "admin-detail";
    }
    @PostMapping("/order/detail/delivery/{id}")
    public String deliveryOrder(@PathVariable("id") int id){
        Bill bill = billRepository.findById(id);
        bill.setStatus(true);
        billRepository.save(bill);
        return "redirect:/admin/order";
    }
    @GetMapping("/sales")
    public String sales(Model model){
        List<Bill> billList = billRepository.findAllByStatus(true);
        int sum = 0;
        for(Bill bill : billList)
            sum += bill.getSum();
        model.addAttribute("billList", billList);
        model.addAttribute("sum",sum);
        return "admin-doanhthu";
    }
//    @GetMapping("/nhanvien")
//    public String nhanvien(Model model){
//        List<User> list = userRepository.findAll();
//        for (User user : list) {
//            for (Role role : user.getRoles()) {
//                System.out.println(role);
//                if(role.equals("ROLE_STAFF"))
//                    list.remove(user);
//            }
//        }
//        model.addAttribute("accounts", list);
//
//        return "/admin-nv";
//    }
//    @GetMapping("/nhanvien/add")
//    public String ThemNhanVien(HttpServletRequest request, Model model){
//        if(request.isUserInRole("ADMIN")){
//            model.addAttribute("userForm", new UserForm());
//            return "/add-nv";
//        }
//        return "/admin-nv";
//    }
//    @PostMapping("/nhanvien/register")
//    public String register(@Valid UserForm userForm){
//        System.out.println(userForm.getEmail());
//        if(userRepository.findByEmail(userForm.getEmail()) == null){
//            User oth = new User();
//            oth.setEmail(userForm.getEmail());
//            oth.setPassword(passwordEncoder.encode(userForm.getPassword()));
//            userRepository.save(oth);
//            UserInfo userInfo = new UserInfo(userForm.getUserName(), userForm.getPhone(), userForm.getAddress());
//            oth.setUserInfo(userInfo);
//            userInfo.setUser(oth);
//            HashSet<Role> roles = new HashSet<>();
//            roles.add(roleRepository.findByRoleName("ROLE_STAFF"));
//            oth.setRoles(roles);
//            userRepository.save(oth);
//            return "redirect:/admin-nv";
//        }
//        return "redirect:/signup?error";
//    }
}
