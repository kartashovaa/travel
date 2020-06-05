package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.repository.CityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final ResortService resortService;
    private final UserService userService;

    public CityService(CityRepository cityRepository, ResortService resortService, UserService userService) {
        this.cityRepository = cityRepository;
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
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent())
            return city.get();
        else
            throw new NoSuchElementException();
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public void update(City city) {
        if (cityRepository.findById(city.getId()).isPresent()) {
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
        throwIfCantDelete(user, getById(id), resortService.findByArrivalCity(city),
                resortService.findByDepartureCity(city), userService.getAll());

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
