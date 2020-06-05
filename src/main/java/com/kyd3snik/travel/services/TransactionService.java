package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.ResortTransaction;
import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResortTransaction getById(long id) {
        Optional<ResortTransaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent())
            return transaction.get();
        else
            throw new NoSuchElementException();
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
