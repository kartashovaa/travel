package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "Контроллер для демонстрации работы репозиториев")
@RestController
public class DemoController {
    private HotelRepository hotelRepository;
    private CountryRepository countryRepository;
    private HotelRoomRepository hotelRoomRepository;
    private ResortRepository resortRepository;
    private TagRepository tagRepository;
    private UserRepository userRepository;

    DemoController(HotelRepository hotelRepository, CityRepository cityRepository, CountryRepository countryRepository,
                   HotelRoomRepository hotelRoomRepository, UserRepository userRepository,
                   ResortRepository resortRepository, TagRepository tagRepository) {
        this.hotelRepository = hotelRepository;
        this.countryRepository = countryRepository;
        this.hotelRoomRepository = hotelRoomRepository;
        this.userRepository = userRepository;
        this.resortRepository = resortRepository;
        this.tagRepository = tagRepository;
    }

    @ApiOperation("Получение всех отелей")
    @GetMapping("/hotels")
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @ApiOperation("Получение всех стран")
    @GetMapping("/countries")
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @ApiOperation("Получение всех комнат отелей")
    @GetMapping("/hotelRooms")
    public List<HotelRoom> getHotelRooms() {
        return hotelRoomRepository.findAll();
    }

    @ApiOperation("Получение всех курортов")
    @GetMapping("/")
    public List<Resort> getResorts() {
        return resortRepository.findAll();
    }

    @ApiOperation("Получение всех тегов")
    @GetMapping("/tags")
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    @ApiOperation("Получение всех пользователей")
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
