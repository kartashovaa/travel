package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.model.Entertainment;
import com.kyd3snik.travel.repository.HotelRepository;
import com.kyd3snik.travel.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;
    private final HotelRepository hotelService;
    private final ResortService resortService;
    private final CountryService countryService;
    private final EntertainmentService entertainmentService;

    public CityController(
            CityService cityService,
            HotelRepository hotelService,
            ResortService resortService,
            CountryService countryService,
            EntertainmentService entertainmentService
    ) {
        this.cityService = cityService;
        this.hotelService = hotelService;
        this.resortService = resortService;
        this.countryService = countryService;
        this.entertainmentService = entertainmentService;
    }


    @GetMapping
    public ModelAndView getCities() {
        ModelAndView modelAndView = new ModelAndView("cities");
        modelAndView.addObject("cities", cityService.getAll());
        modelAndView.addObject("isModerator",
                AuthService.isAuthenticated() && AuthService.getUser().isModerator());
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchByTitle(@RequestParam("search") String title) {
        ModelAndView modelAndView = new ModelAndView("cities");
        modelAndView.addObject("cities", cityService.searchByTitle(title));
        modelAndView.addObject("isModerator",
                AuthService.isAuthenticated() && AuthService.getUser().isModerator());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getCity(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("city");
        City city = cityService.getById(id);
        modelAndView.addObject("city", city);
        modelAndView.addObject("hotels", hotelService.findByCity(city));
        modelAndView.addObject("resorts", resortService.findByArrivalCity(city));

        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addCity() {
        ModelAndView modelAndView = new ModelAndView("addCity");
        modelAndView.addObject("countries", countryService.getAll());
        modelAndView.addObject("entertainments", entertainmentService.getAll());
        return modelAndView;
    }

    @PostMapping("/add")
    public String addCity(
            @RequestParam("title") String title,
            @RequestParam("country") long idCountry,
            @RequestParam HashMap<String, String> params) {
        Country country = countryService.getById(idCountry);
        List<Entertainment> entertainments = params.keySet().stream()
                .filter(key -> key.startsWith("entertainment"))
                .map(countryKey -> countryKey.substring(13))
                .map(Integer::valueOf)
                .map(entertainmentService::getById)
                .collect(Collectors.toList());
        cityService.addCity(new City(0, title, country, entertainments));
        return "redirect:/cities/add";
    }

}
