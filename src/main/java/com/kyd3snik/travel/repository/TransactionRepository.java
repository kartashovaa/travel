package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.ResortTransaction;
import com.kyd3snik.travel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<ResortTransaction, Long> {
    List<ResortTransaction> findByUser(User user);
}
