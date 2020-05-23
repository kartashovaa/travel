package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.services.*;
import com.kyd3snik.travel.util.DateUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class FrontController {

    private CityService cityService;
    private CountryService countryService;
    private ResortService resortService;
    private TagService tagService;
    private HotelService hotelService;
    private HotelRoomService hotelRoomService;
    private FacilityService facilityService;
    private EntertainmentService entertainmentService;

    public FrontController(CityService cityService, CountryService countryService, ResortService resortService,
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

    @PostMapping("/searchResult")
    public ModelAndView search(@RequestParam HashMap<String, String> params, Authentication auth) {
        int personCount = Integer.parseInt(params.get("personCount"));
        int minCost = Integer.parseInt(params.get("minCost"));
        int maxCost = Integer.parseInt(params.get("maxCost"));
        int minDuration = Integer.parseInt(params.get("minDuration"));
        int maxDuration = Integer.parseInt(params.get("maxDuration"));
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(params.get("startDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SortType sortType = SortType.valueOf(params.get("sortType"));
        byte minStar = Byte.parseByte(params.get("minStar"));

        List<Tag> tags = params.keySet().stream()
                .filter(key -> key.startsWith("tag"))
                .map(tagKey -> tagKey.substring(3))
                .map(Integer::valueOf)
                .map(tagService::getById)
                .collect(Collectors.toList());

        List<Country> countries = params.keySet().stream()
                .filter(key -> key.startsWith("country"))
                .map(countryKey -> countryKey.substring(7))
                .map(Integer::valueOf)
                .map((id) -> countryService.getById((long) id))
                .collect(Collectors.toList());

        List<City> cities = params.keySet().stream()
                .filter(key -> key.startsWith("city"))
                .map(countryKey -> countryKey.substring(4))
                .map(Integer::valueOf)
                .map((id) -> cityService.getById((long) id))
                .collect(Collectors.toList());

        List<EntertainmentOld> entertainments = params.keySet().stream()
                .filter(key -> key.startsWith("entertainment"))
                .map(countryKey -> countryKey.substring(13))
                .map(Integer::valueOf)
                .map(entertainmentService::getById)
                .collect(Collectors.toList());

        List<FacilityOld> facilities = params.keySet().stream()
                .filter(key -> key.startsWith("facility"))
                .map(countryKey -> countryKey.substring(8))
                .map(Integer::valueOf)
                .map(facilityService::getById)
                .collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView("searchResult");
        List<Resort> resorts = new ArrayList<>(resortService.search(minCost, maxCost,
                minDuration, maxDuration, startDate,
                sortType, tags, countries, cities, entertainments, minStar, facilities));

        modelAndView.addObject("isUserAuthenticated", auth != null);
        modelAndView.addObject("resorts", resorts);
//        modelAndView.addObject("personCount", personCount);
        modelAndView.addObject("minCost", minCost);
        modelAndView.addObject("maxCost", maxCost);
        modelAndView.addObject("minDuration", minDuration);
        modelAndView.addObject("maxDuration", maxDuration);
        modelAndView.addObject("startDate", startDate);
        modelAndView.addObject("sortType", sortType);
        modelAndView.addObject("minStar", minStar);

        modelAndView.addObject("tags", tagService.getAll().stream()
                .map(tag -> new SelectableData<Tag>(tag, tags.contains(tag)))
                .collect(Collectors.toList())
        );
        modelAndView.addObject("countries", countryService.getAll().stream()
                .map(country -> new SelectableData<Country>(country, countries.contains(country)))
                .collect(Collectors.toList())
        );
        modelAndView.addObject("cities", cityService.getAll().stream()
                .map(city -> new SelectableData<City>(city, cities.contains(city)))
                .collect(Collectors.toList())
        );
        //TODO: заменить на сервисы
        modelAndView.addObject("entertainments", Arrays.stream(EntertainmentOld.values())
                .map(entertainment -> new SelectableData<EntertainmentOld>(entertainment, entertainments.contains(entertainment)))
                .collect(Collectors.toList())
        );
        modelAndView.addObject("facilities", Arrays.stream(FacilityOld.values())
                .map(ficility -> new SelectableData<FacilityOld>(ficility, false))
                .collect(Collectors.toList())
        );


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
        modelAndView.addObject("entertainments", entertainmentService.getAll());
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
        modelAndView.addObject("facilities", facilityService.getAll());
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

    @GetMapping("/successfulRegistration")
    public String success() {
        return "successfulRegistration";
    }

    private ModelAndView getResortsSearchParameters(ModelAndView modelAndView) {
        modelAndView.addObject("entertainments", List.of(EntertainmentOld.values()));
        modelAndView.addObject("facilities", List.of((FacilityOld.values())));
        modelAndView.addObject("cities", cityService.getAll());
        modelAndView.addObject("countries", countryService.getAll());
        modelAndView.addObject("tags", tagService.getAll());
        return modelAndView;
    }
}
