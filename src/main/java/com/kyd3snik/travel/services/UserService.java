package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
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
}
