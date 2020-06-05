package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByTitleContainingIgnoreCase(String pattern);
}
