package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.repository.CountryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public void update(Country country) {
        boolean exists = countryRepository.existsById(country.getId());
        if (exists) {
            countryRepository.save(country);
        } else {
            throw new EntityNotFoundException("Country not found!");
        }
    }

    public void delete(long id) {
        countryRepository.deleteById(id);
    }
}
