package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Entertainment;
import com.kyd3snik.travel.repository.EntertainmentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EntertainmentService {

    private EntertainmentRepository entertainmentRepository;

    public EntertainmentService(EntertainmentRepository entertainmentRepository) {
        this.entertainmentRepository = entertainmentRepository;
    }

    public void addEntertainment(Entertainment entertainment) {
        entertainmentRepository.save(entertainment);
    }

    public Entertainment getById(long id) {
        return entertainmentRepository.findById(id).get();
    }

    public List<Entertainment> getAll() {
        return entertainmentRepository.findAll();
    }

    public void update(Entertainment entertainment1) {
        boolean exists = entertainmentRepository.existsById(entertainment1.getId());
        if (exists) {
            entertainmentRepository.save(entertainment1);
        } else {
            throw new EntityNotFoundException("Entertainment not found!");
        }
    }

    public void delete(long id) {
        entertainmentRepository.deleteById(id);
    }
}
