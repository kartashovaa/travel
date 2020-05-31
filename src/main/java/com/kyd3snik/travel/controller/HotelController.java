package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Hotel;
import com.kyd3snik.travel.model.HotelRoom;
import com.kyd3snik.travel.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final CityService cityService;
    private final HotelRoomService hotelRoomService;
    private final ResortService resortService;

    public HotelController(HotelService hotelService, CityService cityService, HotelRoomService hotelRoomService, ResortService resortService) {
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.hotelRoomService = hotelRoomService;
        this.resortService = resortService;
    }

    @GetMapping
    public ModelAndView getHotels() {
        ModelAndView modelAndView = new ModelAndView("hotels");
        modelAndView.addObject("hotels", hotelService.getAll());
        modelAndView.addObject("isModerator",
                AuthService.isAuthenticated() && AuthService.getUser().isModerator());
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchByTitle(@RequestParam("search") String title) {
        ModelAndView modelAndView = new ModelAndView("hotels");
        modelAndView.addObject("hotels", hotelService.searchByTitle(title));
        modelAndView.addObject("isModerator",
                AuthService.isAuthenticated() && AuthService.getUser().isModerator());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getHotelDetails(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("hotel");
        modelAndView.addObject("hotel", hotelService.getById(id));
        return modelAndView;

    }

    @GetMapping("/{id}/rooms")
    public ModelAndView getHotelRooms(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("hotelRooms");
        Hotel hotel = hotelService.getById(id);
        modelAndView.addObject("hotelRooms", hotel.getRooms());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addHotel() {
        ModelAndView modelAndView = new ModelAndView("addHotel");
        modelAndView.addObject("cities", cityService.getAll());
        return modelAndView;
    }

    @GetMapping("/{hotelId}/rooms/{roomId}")
    public ModelAndView getHotelRoom(@PathVariable("roomId") long id) {
        ModelAndView modelAndView = new ModelAndView("hotelRoom");
        modelAndView.addObject("hotelRoom", hotelRoomService.getById(id));
        return modelAndView;
    }


    @GetMapping("/buyingHotelRoom/{id}")
    public ModelAndView buyingHotelRoom(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("buyingHotelRoom");
        modelAndView.addObject("hotelRoom", hotelRoomService.getById(id));
        return modelAndView;
    }

    @GetMapping("/hotels/{id}")
    public ModelAndView getHotel(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("hotel");
        modelAndView.addObject("hotel", hotelService.getById(id));
        return modelAndView;
    }

    @PostMapping("/add")
    public String addHotel(
            @RequestParam("title") String title,
            @RequestParam("city") long idCity,
            @RequestParam("address") String address,
            @RequestParam("stars") byte stars,
            @RequestParam HashMap<String, String> params) {
        City city = cityService.getById(idCity);
        hotelService.addHotel(new Hotel(0, title, city, address, stars, new ArrayList<HotelRoom>()));
        return "redirect:/hotels/add";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteHotel(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("deleteHotel");
        modelAndView.addObject("hotel", hotelService.getById(id));
        modelAndView.addObject("resorts", resortService.findByHotel(hotelService.getById(id)));
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteHotelPost(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("deleteHotel");
        modelAndView.addObject("hotel", hotelService.getById(id));
        modelAndView.addObject("resorts", resortService.findByHotel(hotelService.getById(id)));

        try {
            hotelService.delete(id);
            modelAndView.addObject("isSuccessful", true);
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", e.getMessage());
        }
        return modelAndView;
    }
}
