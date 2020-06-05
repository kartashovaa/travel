package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.repository.CountryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CityService cityService;
    private final UserService userService;
    private final ResortService resortService;

    public CountryService(CountryRepository countryRepository, CityService cityService, UserService userService, ResortService resortService) {
        this.countryRepository = countryRepository;
        this.cityService = cityService;
        this.userService = userService;
        this.resortService = resortService;
    }

    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public List<Country> searchByTitle(String string) {
        return countryRepository.findByTitleContainingIgnoreCase(string);
    }

    public Country getById(Long id) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent())
            return country.get();
        else
            throw new NoSuchElementException();
    }

    public void update(Country country) {
        if (countryRepository.findById(country.getId()).isPresent()) {
            countryRepository.save(country);
        } else {
            throw new EntityNotFoundException("Country not found!");
        }
    }

    public void delete(long id) {
        User user = AuthService.getUser();
        Country country = getById(id);
        List<City> cities = cityService.getAllCitiesInCountry(country);
        List<Resort> resortsArrival = new ArrayList<>();
        List<Resort> resortsDeparture = new ArrayList<>();
        for (City city : cities
        ) {
            resortsArrival.addAll(resortService.findByArrivalCity(city));
            resortsDeparture.addAll(resortService.findByDepartureCity(city));
        }

        throwIfCantDelete(user, userService.getAll(), cities, resortsArrival, resortsDeparture);

        countryRepository.deleteById(id);
    }

    private void throwIfCantDelete(User tempUser, List<User> users, List<City> cities, List<Resort> resortsArrival,
                                   List<Resort> resortsDeparture) {
        if (tempUser == null)
            throw new IllegalStateException("Пользователь не авторизован!");
        for (User user : users
        ) {
            if (cities.contains(user.getCity())) {
                throw new IllegalStateException("Невозможно удалить страну, т.к. зарегистрированы пользователи из этой страны");
            }
        }
        for (Resort resort : resortsArrival) {
            if (resort.isPurchased())
                throw new IllegalStateException("Невозможно удалить страну, т.к. есть курорты в эту страну, которые уже куплены");
        }
        for (Resort resort : resortsDeparture) {
            if (resort.isPurchased())
                throw new IllegalStateException("Невозможно удалить страну, т.к. есть курорты из этой страны, которые уже куплены");
        }
    }
}