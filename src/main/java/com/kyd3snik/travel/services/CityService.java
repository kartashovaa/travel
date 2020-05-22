package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.repository.CityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void addCity(City city) {
        cityRepository.save(city);
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

    public void delete(long id) {
        cityRepository.deleteById(id);
    }
}
