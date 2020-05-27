package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.Entertainment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntertainmentRepository extends JpaRepository<Entertainment, Long> {
}
