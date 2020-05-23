package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.model.request.SignUpRequest;
import com.kyd3snik.travel.services.AuthService;
import com.kyd3snik.travel.services.CityService;
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
    private final CityService cityService;

    public AuthController(AuthService authService, CityService cityService) {
        this.authService = authService;
        this.cityService = cityService;
    }


    @GetMapping("/signin")
    String signin(Authentication auth) {
        return auth == null ? "signin" : "redirect:/";
    }

    @GetMapping("/signup")
    ModelAndView signup(Authentication auth) {
        ModelAndView modelAndView = new ModelAndView("signup");
        if (auth != null) {
            modelAndView.setView(new RedirectView("/"));
            return modelAndView;

        }
        modelAndView.addObject("cities", cityService.getAll());
        return modelAndView;
    }

    @PostMapping("/signup")
    ModelAndView signup(HttpServletRequest request, SignUpRequest user) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/successfulRegistration"));
        try {
            authService.signUpUser(user, new WebAuthenticationDetails(request));
        } catch (Exception ex) {
            modelAndView.setViewName("signup");
            modelAndView.addObject("errorMessage", ex.getMessage());
        }

        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView getPersonalAccount(Authentication auth) {
        ModelAndView modelAndView = new ModelAndView("personalAccount");
        User user = authService.getUserByEmail(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
