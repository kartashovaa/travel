package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.services.*;
import com.kyd3snik.travel.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/resorts")
public class ResortController {
    private final ResortService resortService;
    private final HotelService hotelService;
    private final CityService cityService;
    private final TagService tagService;
    private final HotelRoomService hotelRoomService;
    private final UserService userService;
    private final TransactionService transactionService;

    public ResortController(ResortService resortService, HotelService hotelService, CityService cityService, TagService tagService, HotelRoomService hotelRoomService, UserService userService, TransactionService transactionService) {
        this.resortService = resortService;
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.tagService = tagService;
        this.hotelRoomService = hotelRoomService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public ModelAndView getResorts() {
        ModelAndView modelAndView = new ModelAndView("resorts");
        modelAndView.addObject("resorts", resortService.getAllAvailable());
        modelAndView.addObject("isModerator",
                AuthService.isAuthenticated() && AuthService.getUser().isModerator());
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
        modelAndView.addObject("isUserAuthenticated", AuthService.isAuthenticated());
        return modelAndView;
    }

    @PostMapping("/{id}/buy")
    public ModelAndView buyingResortPost(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("buyingResort");
        modelAndView.addObject("resort", resortService.getById(id));
        modelAndView.addObject("isUserAuthenticated", AuthService.isAuthenticated());

        Resort resort = resortService.getById(id);
        try {
            userService.buyResort(resort);
            modelAndView.addObject("isSuccessful", true);
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/{id}/cancellation")
    public ModelAndView purchaseCancellation(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("purchaseCancellation");
        modelAndView.addObject("transaction", transactionService.getById(id));
        modelAndView.addObject("resort", transactionService.getById(id).getResort());
        //TODO: Нужно сделать доступ только для того пользователя, который купил этот курорт
        return modelAndView;
    }

    @PostMapping("/{id}/cancellation")
    public ModelAndView purchaseCancellationPost(@PathVariable("id") long id) {
        ResortTransaction transaction = transactionService.getById(id);
        Resort resort = transaction.getResort();
        ModelAndView modelAndView = new ModelAndView("purchaseCancellation");
        modelAndView.addObject("resort", resort);
        modelAndView.addObject("isUserAuthenticated", AuthService.isAuthenticated());

        try {
            userService.cancelPurchase(transaction);
            modelAndView.addObject("isSuccessful", true);
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", e.getMessage());
        }
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

    @PostMapping("/add")
    public ModelAndView addResort(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("departureCity") long idDepartureCity,
            @RequestParam("arrivalCity") long idArrivalCity,
            @RequestParam("hotel") long idHotel,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam("startDate") Date startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam("endDate") Date endDate,
            @RequestParam("cost") float cost,
            @RequestParam("personCount") byte personCount,
            @RequestParam HashMap<String, String> params) {
        ModelAndView modelAndView = new ModelAndView("addResort");
        City departureCity = cityService.getById(idDepartureCity);
        City arrivalCity = cityService.getById(idArrivalCity);
        Hotel hotel = hotelService.getById(idHotel);

        List<Tag> tags = params.keySet().stream()
                .filter(key -> key.startsWith("tag"))
                .map(countryKey -> countryKey.substring(3))
                .map(Integer::valueOf)
                .map(tagService::getById)
                .collect(Collectors.toList());

        resortService.addResort(new Resort(0, title, description, departureCity, arrivalCity, tags, hotel,
                DateUtil.getPeriod(startDate, endDate), startDate, endDate, cost, personCount, false));
        modelAndView.addObject("isSuccessful", true);
        return modelAndView;
    }
}
