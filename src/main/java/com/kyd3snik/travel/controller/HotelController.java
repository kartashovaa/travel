package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Hotel;
import com.kyd3snik.travel.model.HotelRoom;
import com.kyd3snik.travel.services.CityService;
import com.kyd3snik.travel.services.FacilityService;
import com.kyd3snik.travel.services.HotelRoomService;
import com.kyd3snik.travel.services.HotelService;
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
    private final FacilityService facilityService;
    private final HotelRoomService hotelRoomService;

    public HotelController(HotelService hotelService, CityService cityService, FacilityService facilityService, HotelRoomService hotelRoomService) {
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.facilityService = facilityService;
        this.hotelRoomService = hotelRoomService;
    }

    @GetMapping
    public ModelAndView getHotels() {
        ModelAndView modelAndView = new ModelAndView("hotels");
        modelAndView.addObject("hotels", hotelService.getAll());
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


//    @GetMapping("/addHotelRoom")
//    public ModelAndView addHotelRoom() {
//        ModelAndView modelAndView = new ModelAndView("addHotelRoom");
//        modelAndView.addObject("hotels", hotelService.getAll());
//        modelAndView.addObject("facilities", facilityService.getAll());
//        return modelAndView;
//    }

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
}
