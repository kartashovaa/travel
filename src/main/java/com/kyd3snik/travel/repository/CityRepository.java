package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByTitleContainingIgnoreCase(String title);

    List<City> findByCountry(Country country);
}
