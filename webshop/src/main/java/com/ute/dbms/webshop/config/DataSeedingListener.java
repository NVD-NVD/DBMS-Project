package com.ute.dbms.webshop.config;

import java.util.HashSet;
import java.util.List;

import com.ute.dbms.webshop.entity.Product;
import com.ute.dbms.webshop.entity.Role;
import com.ute.dbms.webshop.entity.User;
import com.ute.dbms.webshop.repository.ProductRepository;
import com.ute.dbms.webshop.repository.RoleRepository;
import com.ute.dbms.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeedingListener implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public void run(String...args) throws Exception{
        // Roles
        if (roleRepository.findByRoleName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByRoleName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER"));
        }

        // Admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("12345678"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("ROLE_ADMIN"));
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // User account
        if (userRepository.findByEmail("user@gmail.com") == null) {
            User user = new User();
            user.setEmail("user@gmail.com");
            user.setPassword(passwordEncoder.encode("pass"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            user.setRoles(roles);
            userRepository.save(user);
        }
        // Products
        if(productRepository.findAll() == null){
            Product product1 = new Product("item1", 350000,"ÁO SƠ MI KHOÁC NGOÀI", 50, "images/products/item_1.jpg");
            productRepository.save(product1);
            Product product2 = new Product("item2", 500000,"SET DẠO PHỐ", 50, "images/products/item_2.jpg");
            productRepository.save(product2);
            Product product3 = new Product("item3", 250000,"ĐẦM XUÔNG", 50, "images/products/item_3.jpg");
            productRepository.save(product3);
            Product product4 = new Product("item4", 250000,"ĐẦM XUÔNG", 50, "images/products/item_4.jpg");
            productRepository.save(product4);
            Product product5 = new Product("item5", 250000,"ĐẦM XUÔNG", 50, "images/products/item_5.jpg");
            productRepository.save(product5);
        }
    }
}
