package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.repository.CountryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
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
        return countryRepository.findById(id).orElseThrow(() -> new IllegalStateException("Страна не найдена"));
    }

    public void update(Country country) {
        if (countryRepository.findById(country.getId()).isPresent()) {
            countryRepository.save(country);
        } else {
            throw new EntityNotFoundException("Страна не найдена!");
        }
    }

    public void delete(long id) {
        try {
            countryRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalStateException("Невозможно удалить страну, т.к. зарегистрированы пользователи из этой страны или куплен курорт в эту страну");
        }
    }
}