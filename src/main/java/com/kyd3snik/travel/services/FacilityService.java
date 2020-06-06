package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Facility;
import com.kyd3snik.travel.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public Facility getById(long id) {
        return facilityRepository.findById(id).orElseThrow(() -> new IllegalStateException("Удобство не найдено"));
    }

    public List<Facility> getAll() {
        return facilityRepository.findAll();
    }

    public void update(Facility facility) {
        if (facilityRepository.findById(facility.getId()).isPresent()) {
            facilityRepository.save(facility);
        } else {
            throw new EntityNotFoundException("Удобство не найдено");
        }
    }

    public void delete(long id) {
        facilityRepository.deleteById(id);
    }
}
