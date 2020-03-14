package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
