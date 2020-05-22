package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Entertainment1;
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

    public void addEntertainment(Entertainment1 entertainment) {
        entertainmentRepository.save(entertainment);
    }

    public Entertainment1 getById(long id) {
        return entertainmentRepository.findById(id).get();
    }

    public List<Entertainment1> getAll() {
        return entertainmentRepository.findAll();
    }

    public void update(Entertainment1 entertainment1) {
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
