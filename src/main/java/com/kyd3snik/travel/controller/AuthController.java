package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.model.request.SignUpRequest;
import com.kyd3snik.travel.services.AuthService;
import com.kyd3snik.travel.services.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    private final AuthService authService;
    private final CityService cityService;

    public AuthController(AuthService authService, CityService cityService) {
        this.authService = authService;
        this.cityService = cityService;
    }


    @GetMapping("/signin")
    String signin() {
        return AuthService.isAuthorized() ? "redirect:/" : "signin";
    }

    @GetMapping("/signup")
    ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        if (AuthService.isAuthorized()) {
            modelAndView.setView(new RedirectView("/"));
            return modelAndView;

        }
        modelAndView.addObject("cities", cityService.getAll());
        return modelAndView;
    }

    @PostMapping("/signup")
    ModelAndView signup(SignUpRequest user) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/successfulRegistration"));
        try {
            authService.signUpUser(user);
        } catch (Exception ex) {
            modelAndView.setViewName("signup");
            modelAndView.addObject("errorMessage", ex.getMessage());
        }

        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("personalAccount");
        User user = AuthService.getUser();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/successfulRegistration")
    public String success() {
        return "successfulRegistration";
    }
}
