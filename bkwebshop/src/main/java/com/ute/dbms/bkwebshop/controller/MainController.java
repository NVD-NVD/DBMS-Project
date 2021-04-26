package com.ute.dbms.bkwebshop.controller;

import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import com.ute.dbms.bkwebshop.entity.Role;
import com.ute.dbms.bkwebshop.entity.User;
import com.ute.dbms.bkwebshop.repository.RoleRepository;
import com.ute.dbms.bkwebshop.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api")
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired 
    private PasswordEncoder passwordEncoder;
    @GetMapping(value = "/")
    public String repuestMenthondName() {
        return "Wellcome project dbms nhom 9";
    }
    // @GetMapping(value = "/login")         
    // public Long requestMethodName() {
    //     User user = userRepository.findByEmail("admin@gmail.com");
    //     System.out.println(user.getEmail());
    //     System.out.println(passwordEncoder.upgradeEncoding(user.getPassword()));
    //     return user.getId();
    // }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User other = userRepository.findByEmail(user.getEmail());
        if (null == other || !new BCryptPasswordEncoder().matches(user.getPassword(), other.getPassword())) {
            System.out.println("login false");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
        }
        
        return ResponseEntity.ok(user.toString());
    }
    @GetMapping(value="/info")      
    public String getMethodName(@RequestBody User user) {
        
        return user.toString();
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody User newUser){
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if(user.equals(newUser))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tai khoan da ton tai");
        }
        HashSet<Role> roles = new HashSet<>();
        
        roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
        newUser.setRoles(roles);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("SUCCSESS" + newUser.toString());
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEntity(@RequestBody User user){
        User other = userRepository.findByEmail(user.getEmail());
        if(other != null){
            other.setRoles(null);
            userRepository.deleteById(other.getId());
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("khong ton tai");
        if(userRepository.findByEmail(user.getEmail()) != null)
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("false");
        
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Delete");
    }
}
