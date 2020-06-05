package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Entertainment;
import com.kyd3snik.travel.repository.EntertainmentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class EntertainmentService {

    private final EntertainmentRepository entertainmentRepository;

    public EntertainmentService(EntertainmentRepository entertainmentRepository) {
        this.entertainmentRepository = entertainmentRepository;
    }

    public void addEntertainment(Entertainment entertainment) {
        entertainmentRepository.save(entertainment);
    }

    public Entertainment getById(long id) {
        return entertainmentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Развлечение не найдено"));
    }

    public List<Entertainment> getAll() {
        return entertainmentRepository.findAll();
    }

    public void update(Entertainment entertainment) {
        if (entertainmentRepository.findById(entertainment.getId()).isPresent()) {
            entertainmentRepository.save(entertainment);
        } else {
            throw new EntityNotFoundException("Entertainment not found!");
        }
    }

    public void delete(long id) {
        entertainmentRepository.deleteById(id);
    }
}
