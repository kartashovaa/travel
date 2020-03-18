package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository extends JpaRepository<User, Long> {

}
