package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.model.SortType;
import com.kyd3snik.travel.repository.ResortRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

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

    public Collection<Resort> search(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                     SortType sort) {
        TreeSet<Resort> res;
        switch (sort) {
            case COST_DOWN:
                res = new TreeSet<>((o1, o2) -> Long.compare(o2.getCost(), o1.getCost()));
                break;
            case DURATION_UP:
                res = new TreeSet<>(Comparator.comparingInt(Resort::getDurationInDays));
                break;
            case DURATION_DOWN:
                res = new TreeSet<>(Comparator.comparingLong(Resort::getCost);
                break;
            case COST_UP:
            default:
                res = new TreeSet<>((o1, o2) -> Integer.compare(o2.getDurationInDays(), o1.getDurationInDays()));
        }
        res.stream()
                .filter((resort) -> resort.getCost() >= minCost && resort.getCost() <= maxCost)
                .filter((resort) -> resort.getDurationInDays() >= minDuration &&
                        resort.getDurationInDays() <= maxDuration)
                .filter((resort) -> startDate.after(resort.getStartDate()))
                .collect(Collectors.toList());
        return res;
    }

    public Collection<Resort> search(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                     SortType sort, List<Country> acceptableCountries) {
        TreeSet<Resort> res = new TreeSet<>(search(minCost, maxCost, minDuration, maxDuration, startDate, sort));
        res.stream()
                .filter((resort) -> acceptableCountries.contains(resort.getHotel().getCity().getCountry()))
                .collect(Collectors.toList());
        return res;
    }

}
