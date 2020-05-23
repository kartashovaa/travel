package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.services.CountryService;
import com.kyd3snik.travel.services.ResortService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;
    private final ResortService resortService;

    public CountryController(CountryService countryService, ResortService resortService) {
        this.countryService = countryService;
        this.resortService = resortService;
    }

    @GetMapping
    public ModelAndView getCountries() {
        ModelAndView modelAndView = new ModelAndView("countries");
        modelAndView.addObject("countries", countryService.getAll());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getCountry(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("country");
        Country country = countryService.getById(id);
        modelAndView.addObject("country", country);
        modelAndView.addObject("resorts", resortService.getResortsInCountry(country));
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addCountry() {
        return new ModelAndView("addCountry");
    }

}
