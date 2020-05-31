package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.model.Hotel;
import com.kyd3snik.travel.model.Resort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResortRepository extends JpaRepository<Resort, Long> {
    List<Resort> findByArrivalCity(City city);

    List<Resort> findByDepartureCity(City city);

    List<Resort> findByHotel(Hotel hotel);

    List<Resort> findByTitleContainingIgnoreCase(String pattern);

    List<Resort> findByArrivalCity_Country(Country country);
}
