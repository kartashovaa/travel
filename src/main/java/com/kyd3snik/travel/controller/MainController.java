package com.kyd3snik.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @GetMapping("/")
    String main() {
        return "main";
    }

    @GetMapping("/user")
    String helloUser(Authentication authentication,
                     Model model
    ) {
        User user = (User) userDetailsManager.loadUserByUsername(authentication.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword() == null ? "NO_PASSWORD" : user.getPassword());
        return "user";
    }
}
