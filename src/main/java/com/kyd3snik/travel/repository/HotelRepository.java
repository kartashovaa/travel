package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
