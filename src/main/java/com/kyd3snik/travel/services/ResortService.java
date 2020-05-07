package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.repository.ResortRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ResortService {

    private ResortRepository resortRepository;

    public void addResort(Resort resort) {
        resortRepository.save(resort);
    }

    public List<Resort> getAll() {
        return resortRepository.findAll();
    }

    public void update(Resort resort) {
        boolean exists = resortRepository.existsById(resort.getId());
        if (exists) {
            resortRepository.save(resort);
        } else {
            throw new EntityNotFoundException("Resort not found!");
        }
    }

    public void delete(long id) {
        resortRepository.deleteById(id);
    }
}
