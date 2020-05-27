package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.services.*;
import com.kyd3snik.travel.util.DateUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private final CityService cityService;
    private final CountryService countryService;
    private final ResortService resortService;
    private final TagService tagService;
    private final FacilityService facilityService;
    private final EntertainmentService entertainmentService;

    public MainController(CityService cityService, CountryService countryService, ResortService resortService,
                          TagService tagService, FacilityService facilityService,
                          EntertainmentService entertainmentService) {
        this.cityService = cityService;
        this.countryService = countryService;
        this.resortService = resortService;
        this.tagService = tagService;
        this.entertainmentService = entertainmentService;
        this.facilityService = facilityService;
    }

    @GetMapping("/")
    public ModelAndView main(Authentication auth) {
        ModelAndView modelAndView = new ModelAndView("mainPage");
        modelAndView.addObject("isUserAuthenticated", auth != null);
        modelAndView.addObject("today", DateUtil.getToday());
        return getResortsSearchParameters(modelAndView);
    }

    private ModelAndView getResortsSearchParameters(ModelAndView modelAndView) {
        modelAndView.addObject("entertainments", entertainmentService.getAll());
        modelAndView.addObject("facilities", facilityService.getAll());
        modelAndView.addObject("cities", cityService.getAll());
        modelAndView.addObject("countries", countryService.getAll());
        modelAndView.addObject("tags", tagService.getAll());
        return modelAndView;
    }
}
