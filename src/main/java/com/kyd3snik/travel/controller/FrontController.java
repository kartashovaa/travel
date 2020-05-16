package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class FrontController {

    @GetMapping("/hotels/{id}")
    public String main(Model model, @PathVariable("id") long id) {
        Hotel hotel = new Hotel(id,
                "Hotel " + id,
                new City(0,
                        "Voronezh",
                        new Country(0, "Russia", "Description"),
                        Collections.emptyList()
                ),
                "Address",
                (byte) 5,
                List.of(
                        new HotelRoom(0, (byte) 3, List.of(Facility.values()), 1f),
                        new HotelRoom(0, (byte) 1, List.of(Facility.values()), 12f),
                        new HotelRoom(0, (byte) 2, List.of(Facility.values()), 13f)
                )
        );

        model.addAttribute("hotel", hotel);

        return "hotel";
    }
}
