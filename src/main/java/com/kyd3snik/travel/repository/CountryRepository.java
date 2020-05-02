package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
