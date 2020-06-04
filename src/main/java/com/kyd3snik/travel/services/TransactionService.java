package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.ResortTransaction;
import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResortTransaction getById(long id) {
        return transactionRepository.findById(id).get();
    }

    public void save(ResortTransaction transaction) {
        transactionRepository.save(transaction);
    }

    public void delete(ResortTransaction transaction) {
        transactionRepository.delete(transaction);
    }

    public List<ResortTransaction> getTransactions(User user) {
        return transactionRepository.findByUser(user);
    }
}
