package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.model.request.SignUpRequest;
import com.kyd3snik.travel.services.AuthService;
import com.kyd3snik.travel.services.CityService;
import com.kyd3snik.travel.services.TransactionService;
import com.kyd3snik.travel.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;
    private final CityService cityService;
    private final UserService userService;
    private final TransactionService transactionService;

    public AuthController(AuthService authService, CityService cityService, UserService userService, TransactionService transactionService) {
        this.authService = authService;
        this.cityService = cityService;
        this.userService = userService;
        this.transactionService = transactionService;
    }


    @GetMapping("/signin")
    String signin() {
        return AuthService.isAuthenticated() ? "redirect:/" : "signin";
    }

    @GetMapping("/signup")
    ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        if (AuthService.isAuthenticated()) {
            modelAndView.setView(new RedirectView("/"));
            return modelAndView;

        }
        modelAndView.addObject("cities", cityService.getAll());
        return modelAndView;
    }

    @PostMapping("/signup")
    ModelAndView signup(@Valid SignUpRequest request, Errors errors) {
        ModelAndView modelAndView = new ModelAndView("signup");

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .birthDay(request.getBirthday())
                .isMale(request.getGender().equals("man"))
                .hasInternationalPassport(request.getHasInternationalPassport().equals("yes"))
                .email(request.getEmail())
                .role(User.ROLE_USER)
                .city(cityService.getById(request.getCityId()))
                .hasDiscount(false)
                .build();

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().get(0).getDefaultMessage();
            modelAndView.addObject("errorMessage", msg);
            modelAndView.addObject("cities", cityService.getAll());

            return modelAndView;
        }

        try {
            authService.signUpUser(user);
            modelAndView.setView(new RedirectView("/successfulRegistration"));
        } catch (Exception ex) {
            modelAndView.addObject("errorMessage", ex.getMessage());
            modelAndView.addObject("cities", cityService.getAll());
        }

        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("personalAccount");
        User user = AuthService.getUser();
        modelAndView.addObject("user", user);
        modelAndView.addObject("transactions", transactionService.getTransactions(user));

        return modelAndView;
    }

    @GetMapping("/refill")
    public String refill() {
        if (AuthService.isAuthenticated()) {
            userService.refill();
            return "redirect:/profile";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/successfulRegistration")
    public String success() {
        return "successfulRegistration";
    }
}
