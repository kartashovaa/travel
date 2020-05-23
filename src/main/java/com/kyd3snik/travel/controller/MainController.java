package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.services.*;
import com.kyd3snik.travel.util.DateUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private CityService cityService;
    private CountryService countryService;
    private ResortService resortService;
    private TagService tagService;
    private HotelService hotelService;
    private HotelRoomService hotelRoomService;
    private FacilityService facilityService;
    private EntertainmentService entertainmentService;

    public MainController(CityService cityService, CountryService countryService, ResortService resortService,
                          TagService tagService, HotelService hotelService, HotelRoomService hotelRoomService,
                          FacilityService facilityService, EntertainmentService entertainmentService) {
        this.cityService = cityService;
        this.countryService = countryService;
        this.resortService = resortService;
        this.tagService = tagService;
        this.hotelService = hotelService;
        this.hotelRoomService = hotelRoomService;
        this.entertainmentService = entertainmentService;
        this.facilityService = facilityService;
    }

    @GetMapping("/")
    public ModelAndView main(Authentication auth) {
        ModelAndView modelAndView = new ModelAndView("mainPage");
        modelAndView.addObject("isUserAuthenticated", auth != null);
        modelAndView.addObject("today", DateUtil.getToday());
        City city = cityService.getById(26);
        List<Resort> hotels = resortService.findByArrivalCity(city);
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
