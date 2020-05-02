package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.request.SignUpRequest;
import com.kyd3snik.travel.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/signin")
    String signin(Authentication auth) {
        return auth == null ? "signin" : "main";
    }

    @GetMapping("/signup")
    String signup(Authentication auth) {
        return auth == null ? "signup" : "main";
    }

    @PostMapping("/signup")
    ModelAndView signup(HttpServletRequest request, SignUpRequest user) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("user"));
        try {
            authService.signUpUser(user, new WebAuthenticationDetails(request));
        } catch (Exception ex) {
            modelAndView.setViewName("signup");
            modelAndView.addObject("errorMessage", ex.getMessage());
        }

        return modelAndView;
    }
}
