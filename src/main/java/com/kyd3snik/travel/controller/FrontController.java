package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FrontController {

    private CityService cityService;
    private CountryService countryService;
    private ResortService resortService;
    private TagService tagService;
    private HotelService hotelService;
    private HotelRoomService hotelRoomService;

    public FrontController(CityService cityService, CountryService countryService, ResortService resortService,
                           TagService tagService, HotelService hotelService, HotelRoomService hotelRoomService) {
        this.cityService = cityService;
        this.countryService = countryService;
        this.resortService = resortService;
        this.tagService = tagService;
        this.hotelService = hotelService;
        this.hotelRoomService = hotelRoomService;
    }

    @GetMapping("/cities/{id}")
    public ModelAndView getCity(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("city");
        modelAndView.addObject("city", cityService.getById(id));
        return modelAndView;
    }

    @GetMapping("/countries/{id}")
    public ModelAndView getCountry(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("country");
        modelAndView.addObject("country", countryService.getById(id));
        return modelAndView;
    }

    @GetMapping("/hotels/{id}")
    public ModelAndView getHotel(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("hotel");
        modelAndView.addObject("hotel", hotelService.getById(id));
        return modelAndView;
    }

    @GetMapping("/hotels/{hotelId}/hotelRooms/{roomId}")
    public ModelAndView getHotelRoom(@PathVariable("roomId") long id) {
        ModelAndView modelAndView = new ModelAndView("hotelRoom");
        modelAndView.addObject("hotelRoom", hotelRoomService.getById(id));
        return modelAndView;
    }

    @GetMapping("/resorts/{id}")
    public ModelAndView getResort(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("resort");
        modelAndView.addObject("resort", resortService.getById(id));
        return modelAndView;
    }

    @GetMapping("/buyingResort/{id}")
    public ModelAndView buyingResort(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("buyingResort");
        modelAndView.addObject("resort", resortService.getById(id));
        return modelAndView;
    }

    @GetMapping("/buyingHotelRoom/{id}")
    public ModelAndView buyingHotelRoom(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("buyingHotelRoom");
        modelAndView.addObject("hotelRoom", hotelRoomService.getById(id));
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("cities", cityService.getAll());
        return modelAndView;
    }

    @GetMapping("/logIn")
    public ModelAndView logIn() {
        return new ModelAndView("logIn");
    }

    //TODO: Сделать нормальную версию с доступом в зависимости от авторизации
    @GetMapping("/personalAccount")
    public ModelAndView getPersonalAccount() {
        ModelAndView modelAndView = new ModelAndView("personalAccount");
        User user = new User(1, "First name", "Last name", "Middle name", new Date(243423123), true, false,
                "Email@gmail.com", cityService.getById(13));
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/main")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("mainPage");
        return getResortsSearchParameters(modelAndView);
    }

    private ModelAndView getResortsSearchParameters(ModelAndView modelAndView) {
        modelAndView.addObject("entertainments", List.of(Entertainment.values()));
        modelAndView.addObject("facilities", List.of((Facility.values())));
        modelAndView.addObject("cities", cityService.getAll());
        modelAndView.addObject("countries", countryService.getAll());
        modelAndView.addObject("tags", tagService.getAll());
        return modelAndView;
    }

    @PostMapping("/searchResult")
    public ModelAndView search(@RequestParam MultiValueMap<String, String> paramMap) {
        int minCost = Integer.parseInt(paramMap.get("minCost").get(0));
        int maxCost = Integer.parseInt(paramMap.get("maxCost").get(0));
        int minDuration = Integer.parseInt(paramMap.get("minDuration").get(0));
        int maxDuration = Integer.parseInt(paramMap.get("maxDuration").get(0));
        Date startDate = new Date(12312313); //TODO: Parse date
        SortType sortType = SortType.valueOf(paramMap.get("sortType").get(0));

        List<Tag> tags = paramMap.keySet().stream()
                .filter(key -> key.startsWith("tag"))
                .map(tagKey -> tagKey.substring(3))
                .map(Integer::valueOf)
                .map(tagService::getById)
                .collect(Collectors.toList());

        List<Country> countries = paramMap.keySet().stream()
                .filter(key -> key.startsWith("country"))
                .map(countryKey -> countryKey.substring(7))
                .map(Integer::valueOf)
                .map((id) -> countryService.getById((long) id))
                .collect(Collectors.toList());

        List<City> cities = paramMap.keySet().stream()
                .filter(key -> key.startsWith("city"))
                .map(countryKey -> countryKey.substring(4))
                .map(Integer::valueOf)
                .map((id) -> cityService.getById((long) id))
                .collect(Collectors.toList());

        List<Entertainment> entertainments = paramMap.keySet().stream()
                .filter(key -> key.startsWith("entertainment"))
                .map(countryKey -> countryKey.substring(13))
                .map(Entertainment::valueOf)
                .collect(Collectors.toList());

        byte minStar = Byte.parseByte(paramMap.get("minStar").get(0));

        List<Facility> facilities = paramMap.keySet().stream()
                .filter(key -> key.startsWith("facility"))
                .map(countryKey -> countryKey.substring(8))
                .map(Facility::valueOf)
                .collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView("searchResult");
        List<Resort> resorts = new ArrayList<Resort>(resortService.search(minCost, maxCost,
                minDuration, maxDuration, startDate,
                sortType, tags, countries, cities, entertainments, minStar, facilities));

        modelAndView.addObject("resorts", resorts);
        return modelAndView;
    }

    @GetMapping("/searchResult")
    public ModelAndView getSearchResult() {
        ModelAndView modelAndView = new ModelAndView("searchResult");
        return getResortsSearchParameters(modelAndView);
    }

    @GetMapping("/cities")
    public ModelAndView getCities() {
        ModelAndView modelAndView = new ModelAndView("cities");
        modelAndView.addObject("cities", cityService.getAll());
        return modelAndView;
    }

    @GetMapping("/countries")
    public ModelAndView getCountries() {
        ModelAndView modelAndView = new ModelAndView("countries");
        modelAndView.addObject("countries", countryService.getAll());
        return modelAndView;
    }

    @GetMapping("/hotels")
    public ModelAndView getHotels() {
        ModelAndView modelAndView = new ModelAndView("hotels");
        modelAndView.addObject("hotels", hotelService.getAll());
        return modelAndView;
    }

    @GetMapping("/hotels/{id}/hotelRooms")
    public ModelAndView getHotelRooms(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("hotelRooms");
        Hotel hotel = hotelService.getById(id);
        modelAndView.addObject("hotelRooms", hotel.getRooms());
        return modelAndView;
    }

    @GetMapping("/resorts")
    public ModelAndView getResorts() {
        ModelAndView modelAndView = new ModelAndView("resorts");
        modelAndView.addObject("resorts", resortService.getAll());
        return modelAndView;
    }

    @GetMapping("/addCountry")
    public ModelAndView addCountry() {
        return new ModelAndView("addCountry");
    }

    @GetMapping("/addCity")
    public ModelAndView addCity() {
        ModelAndView modelAndView = new ModelAndView("addCity");
        modelAndView.addObject("countries", countryService.getAll());
        modelAndView.addObject("entertainments", List.of(Entertainment.values()));
        return modelAndView;
    }

    @GetMapping("/addHotel")
    public ModelAndView addHotel() {
        ModelAndView modelAndView = new ModelAndView("addHotel");
        modelAndView.addObject("cities", cityService.getAll());
        return modelAndView;
    }

    @GetMapping("/addHotelRoom")
    public ModelAndView addHotelRoom() {
        ModelAndView modelAndView = new ModelAndView("addHotelRoom");
        modelAndView.addObject("hotels", hotelService.getAll());
        modelAndView.addObject("facilities", List.of(Facility.values()));
        return modelAndView;
    }

    @GetMapping("/addResort")
    public ModelAndView addResort() {
        ModelAndView modelAndView = new ModelAndView("addResort");
        modelAndView.addObject("hotels", hotelService.getAll());
        modelAndView.addObject("cities", cityService.getAll());
        modelAndView.addObject("tags", tagService.getAll());
        modelAndView.addObject("hotelRooms", hotelRoomService.getAll());
        return modelAndView;
    }
}
