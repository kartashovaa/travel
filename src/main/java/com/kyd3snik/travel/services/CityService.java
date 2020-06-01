package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.CityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final HotelService hotelService;
    private final ResortService resortService;
    private final UserService userService;

    public CityService(CityRepository cityRepository, HotelService hotelService, ResortService resortService, UserService userService) {
        this.cityRepository = cityRepository;
        this.hotelService = hotelService;
        this.resortService = resortService;
        this.userService = userService;
    }

    public void addCity(City city) {
        cityRepository.save(city);
    }

    public List<City> searchByTitle(String string) {
        return cityRepository.findByTitleContainingIgnoreCase(string);
    }

    public City getById(long id) {
        return cityRepository.findById(id).get();
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public void update(City city) {
        boolean exists = cityRepository.existsById(city.getId());
        if (exists) {
            cityRepository.save(city);
        } else {
            throw new EntityNotFoundException("City not found!");
        }
    }

    public List<City> getAllCitiesInCountry(Country country) {
        return cityRepository.findByCountry(country);
    }

    public void delete(long id) {
        User user = AuthService.getUser();
        City city = getById(id);
        List<Resort> resortsArrival = resortService.findByArrivalCity(city);
        List<Resort> resortsDeparture = resortService.findByDepartureCity(city);
        List<Hotel> hotels = hotelService.findByCity(city);
        throwIfCantDelete(user, city, resortsArrival, resortsDeparture, userService.getAll());

        hotelService.delete(hotels);
        resortService.delete(resortsDeparture);
        cityRepository.deleteById(id);
    }

    public void delete(List<City> cities) {
        cities.forEach(city -> delete(city.getId()));
    }

    private void throwIfCantDelete(User tempUser, City city, List<Resort> resortsArrival, List<Resort> resortsDeparture,
                                   List<User> users) {
        if (tempUser == null)
            throw new IllegalStateException("Пользователь не авторизован!");
        for (User user : users
        ) {
            if (user.getCity() == city) {
                throw new IllegalStateException("Невозможно удалить город, т.к. зарегистрированы пользователи с таким городом");
            }
        }
        for (Resort resort : resortsArrival) {
            if (resort.isPurchased())
                throw new IllegalStateException("Невозможно удалить город, т.к. в нем были куплены курорты!");
        }
        for (Resort resort : resortsDeparture) {
            if (resort.isPurchased())
                throw new IllegalStateException("Невозможно удалить город, т.к. из него были куплены курорты!");
        }
    }
}
