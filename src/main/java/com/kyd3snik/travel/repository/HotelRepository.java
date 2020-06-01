package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByTitleContainingIgnoreCase(String pattern);

    List<Hotel> findByCity(City city);
}
