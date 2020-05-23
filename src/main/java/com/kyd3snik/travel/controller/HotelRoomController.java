package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.Facility;
import com.kyd3snik.travel.model.Hotel;
import com.kyd3snik.travel.model.HotelRoom;
import com.kyd3snik.travel.services.FacilityService;
import com.kyd3snik.travel.services.HotelRoomService;
import com.kyd3snik.travel.services.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hotelRooms")
public class HotelRoomController {
    HotelService hotelService;
    FacilityService facilityService;
    HotelRoomService hotelRoomService;

    public HotelRoomController(HotelService hotelService, FacilityService facilityService,
                               HotelRoomService hotelRoomService) {
        this.hotelService = hotelService;
        this.facilityService = facilityService;
        this.hotelRoomService = hotelRoomService;
    }

    @GetMapping("/add")
    public ModelAndView addHotelRoom() {
        ModelAndView modelAndView = new ModelAndView("addHotelRoom");
        modelAndView.addObject("hotels", hotelService.getAll());
        modelAndView.addObject("facilities", facilityService.getAll());
        return modelAndView;
    }

    @PostMapping("/add")
    public String addHotelRoom(
            @RequestParam("numberOfSleepingPlaces") byte numberOfSleepingPlaces,
            @RequestParam("hotel") long idHotel,
            @RequestParam("cost") float cost,
            @RequestParam HashMap<String, String> params) {
        Hotel hotel = hotelService.getById(idHotel);
        List<Facility> facilities = params.keySet().stream()
                .filter(key -> key.startsWith("facility"))
                .map(countryKey -> countryKey.substring(8))
                .map(Integer::valueOf)
                .map(facilityService::getById)
                .collect(Collectors.toList());
        HotelRoom hotelRoom = new HotelRoom(0, numberOfSleepingPlaces, facilities, cost);
        hotelRoomService.addHotelRoom(hotelRoom);
        hotelService.addHotelRoomToHotel(hotel, hotelRoom);
        return "redirect:/hotelRooms/add";
    }

}
