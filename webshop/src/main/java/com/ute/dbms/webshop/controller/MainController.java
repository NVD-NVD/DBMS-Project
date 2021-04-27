package com.ute.dbms.webshop.controller;

import com.ute.dbms.webshop.entity.User;
import com.ute.dbms.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/")
    public String home1(){
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
//    @GetMapping("/loginTrue")
//    public String getLogin(HttpServletRequest request) {
//        System.out.println(request.toString());
//        return "redirect:/index";
//    }
//    @GetMapping("/loginError")
//    public String loginerror(HttpServletRequest request) {
//        System.out.println(request.toString());
//        return "redirect:/login?error";
//    }
    @GetMapping(value = "/signup")
    public String  signup(){
        return "sign-up";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
