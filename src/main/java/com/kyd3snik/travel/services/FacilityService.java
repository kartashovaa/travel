package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Facility1;
import com.kyd3snik.travel.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FacilityService {

    private FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public void addFacility(Facility1 facility) {
        facilityRepository.save(facility);
    }

    public Facility1 getById(long id) {
        return facilityRepository.findById(id).get();
    }

    public List<Facility1> getAll() {
        return facilityRepository.findAll();
    }

    public void update(Facility1 facility) {
        boolean exists = facilityRepository.existsById(facility.getId());
        if (exists) {
            facilityRepository.save(facility);
        } else {
            throw new EntityNotFoundException("Facility not found!");
        }
    }

    public void delete(long id) {
        facilityRepository.deleteById(id);
    }
}
