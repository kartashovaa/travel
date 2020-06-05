package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Facility;
import com.kyd3snik.travel.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public void addFacility(Facility facility) {
        facilityRepository.save(facility);
    }

    public Facility getById(long id) {
        Optional<Facility> facility = facilityRepository.findById(id);
        if (facility.isPresent())
            return facility.get();
        else
            throw new NoSuchElementException();
    }

    public List<Facility> getAll() {
        return facilityRepository.findAll();
    }

    public void update(Facility facility) {
        if (facilityRepository.findById(facility.getId()).isPresent()) {
            facilityRepository.save(facility);
        } else {
            throw new EntityNotFoundException("Facility not found!");
        }
    }

    public void delete(long id) {
        facilityRepository.deleteById(id);
    }
}
