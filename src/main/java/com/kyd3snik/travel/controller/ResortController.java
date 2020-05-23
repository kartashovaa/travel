package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/resorts")
public class ResortController {
    private final ResortService resortService;
    private final HotelService hotelService;
    private final CityService cityService;
    private final TagService tagService;
    private final HotelRoomService hotelRoomService;

    public ResortController(ResortService resortService, HotelService hotelService, CityService cityService, TagService tagService, HotelRoomService hotelRoomService) {
        this.resortService = resortService;
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.tagService = tagService;
        this.hotelRoomService = hotelRoomService;
    }

    @GetMapping
    public ModelAndView getResorts() {
        ModelAndView modelAndView = new ModelAndView("resorts");
        modelAndView.addObject("resorts", resortService.getAll());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getResort(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("resort");
        modelAndView.addObject("resort", resortService.getById(id));
        return modelAndView;
    }

    @GetMapping("/{id}/buy")
    public ModelAndView buyingResort(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("buyingResort");
        modelAndView.addObject("resort", resortService.getById(id));
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addResort() {
        ModelAndView modelAndView = new ModelAndView("addResort");
        modelAndView.addObject("hotels", hotelService.getAll());
        modelAndView.addObject("cities", cityService.getAll());
        modelAndView.addObject("tags", tagService.getAll());
        modelAndView.addObject("hotelRooms", hotelRoomService.getAll());
        return modelAndView;
    }
}
