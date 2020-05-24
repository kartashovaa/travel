package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.model.ResortTransaction;
import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TransactionService transactionService;

    public UserService(UserRepository userRepository, TransactionService transactionService) {
        this.userRepository = userRepository;
        this.transactionService = transactionService;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getById(long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        boolean exists = userRepository.existsById(user.getId());
        if (exists) {
            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User not found!");
        }
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void buyResort(Resort resort) {
        User user = AuthService.getUser();
        throwIfCantBuy(user, resort);

        user.setBalance(user.getBalance() - resort.getCost());
        userRepository.save(user);
        transactionService.save(new ResortTransaction(0, user, resort));
    }

    private void throwIfCantBuy(User user, Resort resort) {
        if (user == null)
            throw new IllegalStateException("Пользователь не авторизован!");
        if (resort.getArrivalCity().getCountry() != user.getCity().getCountry() && !user.isHasInternationalPassport())
            throw new IllegalStateException("Нет загранпасспорта!");
        if (user.getBalance() < resort.getCost())
            throw new IllegalStateException("Не достаточно средств на счету!");
    }

    public void refill() {
        User user = AuthService.getUser();
        user.setBalance(10000);
        userRepository.save(user);
    }
}
