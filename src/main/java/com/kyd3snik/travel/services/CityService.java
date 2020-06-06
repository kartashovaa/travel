package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.repository.CityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void addCity(City city) {
        cityRepository.save(city);
    }

    public List<City> searchByTitle(String string) {
        return cityRepository.findByTitleContainingIgnoreCase(string);
    }

    public City getById(long id) {
        return cityRepository.findById(id).orElseThrow(() -> new IllegalStateException("Город не найден"));
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public void update(City city) {
        if (cityRepository.findById(city.getId()).isPresent()) {
            cityRepository.save(city);
        } else {
            throw new EntityNotFoundException("Город не найден");
        }
    }

    public List<City> getAllCitiesInCountry(Country country) {
        return cityRepository.findByCountry(country);
    }

    public void delete(long id) {
        try {
            cityRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalStateException("Невозможно удалить город, т.к. в нем зарегистрированы пользователи либо в этот город куплен курорт");
        }
    }

    public void delete(List<City> cities) {
        cities.forEach(city -> delete(city.getId()));
    }
}
