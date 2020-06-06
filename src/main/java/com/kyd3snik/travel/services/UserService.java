package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.model.ResortTransaction;
import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.repository.UserRepository;
import com.kyd3snik.travel.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TransactionService transactionService;
    private final ResortService resortService;

    public UserService(UserRepository userRepository, TransactionService transactionService, ResortService resortService) {
        this.userRepository = userRepository;
        this.transactionService = transactionService;
        this.resortService = resortService;
    }

    public User getById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("Пользователь не найден"));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        boolean exists = userRepository.existsById(user.getId());
        if (exists) {
            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("Пользователь не найден");
        }
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void buyResort(Resort resort) {
        User user = AuthService.getUser();
        throwIfCantBuy(user, resort);

        user.setBalance(user.getBalance() - resort.getCost());
        resort.setPurchased(true);
        resortService.update(resort);
        userRepository.save(user);
        transactionService.save(new ResortTransaction(0, user, resort));
    }

    private void throwIfCantBuy(User user, Resort resort) {
        if (user == null)
            throw new IllegalStateException("Пользователь не авторизован!");
        if (resort.getArrivalCity().getCountry().getId() != user.getCity().getCountry().getId() && !user.isHasInternationalPassport())
            throw new IllegalStateException("Нет загранпасспорта!");
        if (user.getBalance() < resort.getCost())
            throw new IllegalStateException("Не достаточно средств на счету!");
        if (resort.isPurchased())
            throw new IllegalStateException("Данный курорт недоступен, т.к. уже был куплен!");
    }

    public void cancelPurchase(ResortTransaction transaction) {
        User user = AuthService.getUser();
        Resort resort = transaction.getResort();

        throwIfCantCancel(user, resort);

        user.setBalance(user.getBalance() + resort.getCost());
        resort.setPurchased(false);
        resortService.update(resort);
        userRepository.save(user);
        transactionService.delete(transaction);
    }

    private void throwIfCantCancel(User user, Resort resort) {
        if (user == null)
            throw new IllegalStateException("Пользователь не авторизован!");
        if (DateUtil.getPeriod(DateUtil.getToday(), resort.getStartDate()) < 0)
            throw new IllegalStateException("Невозможно отменить покупку, т.к. курорт уже начался!");
        if (DateUtil.getPeriod(DateUtil.getToday(), resort.getStartDate()) <= 1)
            throw new IllegalStateException("Невозможно отменить покупку, т.к. осталось меньше 1 дня до его начала!");
        if (!resort.isPurchased())
            throw new IllegalStateException("Данный курорт не куплен!");
    }

    public void refill() {
        User user = AuthService.getUser();
        user.setBalance(50000);
        userRepository.save(user);
    }
}
