package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
