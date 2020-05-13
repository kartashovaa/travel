package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.model.SortType;
import com.kyd3snik.travel.repository.ResortRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

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
                res = new TreeSet<>(new CostDownComparator());
                break;
            case DURATION_UP:
                res = new TreeSet<>(new DurationUpComparator());
                break;
            case DURATION_DOWN:
                res = new TreeSet<>(new DurationDownComparator());
                break;
            case COST_UP:
            default:
                res = new TreeSet<>(new CostUpComparator());
        }
        for (Resort resort : resortRepository.findAll()
        ) {
            if (resort.getCost() >= minCost && resort.getCost() <= maxCost && resort.getDurationInDays() >= minDuration
                    && resort.getDurationInDays() <= maxDuration && startDate.after(resort.getStartDate())) {
                res.add(resort);
            }
        }
        return res;
    }

    public Collection<Resort> search(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                     SortType sort, List<Country> acceptableCountries) {
        TreeSet<Resort> res = new TreeSet<>(search(minCost, maxCost, minDuration, maxDuration, startDate, sort));
        for (Resort resort : res
        ) {
            if (!acceptableCountries.contains(resort.getHotel().getCity().getCountry())) {
                res.remove(resort);
            }
        }
        return res;
    }

    private static class CostUpComparator implements Comparator<Resort> {

        @Override
        public int compare(Resort o1, Resort o2) {
            return Long.compare(o1.getCost(), o2.getCost());
        }
    }

    private static class CostDownComparator implements Comparator<Resort> {

        @Override
        public int compare(Resort o1, Resort o2) {
            return Long.compare(o2.getCost(), o1.getCost());
        }
    }

    private static class DurationUpComparator implements Comparator<Resort> {

        @Override
        public int compare(Resort o1, Resort o2) {
            return Integer.compare(o1.getDurationInDays(), o2.getDurationInDays());
        }
    }

    private static class DurationDownComparator implements Comparator<Resort> {

        @Override
        public int compare(Resort o1, Resort o2) {
            return Integer.compare(o2.getDurationInDays(), o1.getDurationInDays());
        }
    }
}
