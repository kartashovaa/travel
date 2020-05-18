package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.services.CityService;
import com.kyd3snik.travel.services.CountryService;
import com.kyd3snik.travel.services.ResortService;
import com.kyd3snik.travel.services.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class FrontController {

    private CityService cityService;
    private CountryService countryService;
    private ResortService resortService;
    private TagService tagService;

    public FrontController(CityService cityService, CountryService countryService, ResortService resortService,
                           TagService tagService) {
        this.cityService = cityService;
        this.countryService = countryService;
        this.resortService = resortService;
        this.tagService = tagService;
    }

    @GetMapping("/hotels/{id}")
    public String getHotel(Model model, @PathVariable("id") long id) {
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

   /* @GetMapping("/cities/{id}")
    public String getCity(Model model, @PathVariable("id") long id) {
        City city = new City(id, "City", new Country(0, "Russia", "Description"),
                Collections.emptyList());

        model.addAttribute("city", city);

        return "city";
    }*/

    @GetMapping("/cities/{id}")
    public ModelAndView getCity(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("city");
        City city = new City(id, "City", new Country(0, "Russia", "Description"),
                Collections.emptyList());

        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @GetMapping("/countries/{id}")
    public ModelAndView getCountry(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("country");
        //Country country = countryService.getById(id);
        Country country = new Country(id, "title_1", "description_1");
        modelAndView.addObject("country", country);
        return modelAndView;
    }

    @GetMapping("/hotelRooms/{id}")
    public ModelAndView getHotelRoom(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("hotelRoom");
        HotelRoom hotelRoom = new HotelRoom(id, (byte) 2, Collections.emptyList(), 123f);

        modelAndView.addObject("hotelRoom", hotelRoom);
        return modelAndView;
    }

    @GetMapping("/resorts/{id}")
    public ModelAndView getResort(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("resort");
        Resort resort = new Resort(id, "title", "description", Collections.emptyList(),
                new Hotel(2, "hotel title",
                        new City(5, "title", new Country(7, "Country title",
                                "Country description"), Collections.emptyList()), "address",
                        (byte) 5, Collections.emptyList()), 5, new Date(4364363),
                new Date(4374363), 5463);

        modelAndView.addObject("resort", resort);
        return modelAndView;
    }

    @GetMapping("/main")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("mainPage");
        modelAndView.addObject("entertainments", List.of(Entertainment.values()));
        modelAndView.addObject("facilities", List.of((Facility.values())));
        modelAndView.addObject("cities", cityService.getAll());
        modelAndView.addObject("countries", countryService.getAll());
        modelAndView.addObject("tags", tagService.getAll());
        return modelAndView;
    }

    @PostMapping("/main")
    public ModelAndView search(minSearchRequest searchRequest) {
        ModelAndView modelAndView = new ModelAndView("searchResult");
        List<Resort> resorts = new ArrayList<Resort>(resortService.search(searchRequest.getMinCost(), searchRequest.getMaxCost(),
                searchRequest.getMinDuration(), searchRequest.getMaxDuration(), searchRequest.getStartDate(),
                searchRequest.getSortType()));
        resorts.forEach(modelAndView::addObject);
        return modelAndView;
    }
}
