package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
