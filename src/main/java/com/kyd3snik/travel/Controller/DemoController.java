package com.kyd3snik.travel.Controller;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {
    private HotelRepository hotelRepository;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;
    private HotelRoomRepository hotelRoomRepository;
    private ResortRepository resortRepository;
    private TagRepository tagRepository;
    private UserRepository userRepository;

    DemoController(HotelRepository hotelRepository, CityRepository cityRepository, CountryRepository countryRepository,
                   HotelRoomRepository hotelRoomRepository, UserRepository userRepository,
                   ResortRepository resortRepository, TagRepository tagRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.hotelRoomRepository = hotelRoomRepository;
        this.userRepository = userRepository;
        this.resortRepository = resortRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/hotels")
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/cities")
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("/hotelRooms")
    public List<HotelRoom> getHotelRooms() {
        return hotelRoomRepository.findAll();
    }

    @GetMapping("/")
    public List<Resort> getResorts() {
        return resortRepository.findAll();
    }

    @GetMapping("/tags")
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
