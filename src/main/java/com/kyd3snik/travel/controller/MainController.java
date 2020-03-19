package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.Country;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class MainController {


    @GetMapping("/")
    public String root(Model model) {


        Country voronezh = new Country(0, "Voronezh", "The best country!");
        Country moscow = Country.builder()
                .title("Moscow")
                .description("Tralala")
                .build();

        model.addAttribute("name", "Alex");
        model.addAttribute("voronezh", voronezh);
        model.addAttribute("moscow", moscow);
        model.addAttribute("items", Arrays.asList("1", "2"));

        return "main";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
