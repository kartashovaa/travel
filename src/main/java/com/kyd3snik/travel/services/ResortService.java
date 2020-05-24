package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.ResortRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResortService {

    private ResortRepository resortRepository;

    public ResortService(ResortRepository resortRepository) {
        this.resortRepository = resortRepository;
    }

    public void addResort(Resort resort) {
        resortRepository.save(resort);
    }

    public Resort getById(long id) {
        return resortRepository.findById(id).get();
    }

    public List<Resort> getAll() {
        return resortRepository.findAll();
    }

    public List<Resort> findByArrivalCity(City city) {
        return resortRepository.findByArrivalCity(city);
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

    public List<Resort> getResortsInCountry(Country country) {
        return resortRepository.findByArrivalCity_Country(country);
    }

    public List<Resort> search(SearchModel model) {
        return resortRepository.findAll().stream()
                .filter((resort) -> resort.getCost() >= model.getMinCost() && resort.getCost() <= model.getMaxCost())
                .filter((resort) -> resort.getDurationInDays() >= model.getMinDuration() && resort.getDurationInDays() <= model.getMaxDuration())
                .filter((resort) -> resort.getStartDate().after(model.getStartDate()))
                .filter((resort) -> resort.getTags().containsAll(model.getNecessaryTags()))
                .filter((resort) -> model.getAcceptableCountries().contains(resort.getArrivalCity().getCountry()))
                .filter((resort) -> model.getAcceptableCities().contains(resort.getArrivalCity()))
                .filter((resort) -> resort.getHotel().getCity().getEntertainments().containsAll(model.getNecessaryEntertainments()))
                .filter((resort) -> resort.getHotel().getStars() >= model.getMinStar())
                .filter((resort) -> getAllFacilitiesInHotel(resort.getHotel()).containsAll(model.getNecessaryFacilities()))
                .sorted(getComparator(model.getSortType()))
                .collect(Collectors.toList());
    }

    private List<Facility> getAllFacilitiesInHotel(Hotel hotel) {
        return hotel.getRooms().stream().flatMap(room -> room.getFacilities().stream()).collect(Collectors.toList());
    }

    private Comparator<Resort> getComparator(SortType sort) {
        switch (sort) {
            case COST_DOWN:
                return (o1, o2) -> Float.compare(o2.getCost(), o1.getCost());
            case DURATION_UP:
                return Comparator.comparingLong(Resort::getDurationInDays);
            case DURATION_DOWN:
                return (o1, o2) -> Long.compare(o2.getDurationInDays(), o1.getDurationInDays());
            case COST_UP:
            default:
                return Comparator.comparingDouble(Resort::getCost);
        }
    }
}
