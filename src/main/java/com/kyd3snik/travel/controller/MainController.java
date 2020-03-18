package com.kyd3snik.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class MainController {

    private MainRepository repository;

    @GetMapping("/")
    public String getModel(@RequestParam(name = "name", required = false, defaultValue = "Nope") String name, Model model) {

        model.addAttribute("name", name);
        model.addAttribute("items", Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
        return "main";
    }
}
