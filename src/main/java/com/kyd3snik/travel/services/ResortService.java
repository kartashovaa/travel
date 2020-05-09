package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.model.Sorts;
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

    static class CostUpComparator implements Comparator<Resort> {

        @Override
        public int compare(Resort o1, Resort o2) {
            return Long.compare(o1.getCost(), o2.getCost());
        }
    }

    static class CostDownComparator implements Comparator<Resort> {

        @Override
        public int compare(Resort o1, Resort o2) {
            return Long.compare(o2.getCost(), o1.getCost());
        }
    }

    static class DurationUpComparator implements Comparator<Resort> {

        @Override
        public int compare(Resort o1, Resort o2) {
            return Integer.compare(o1.getDurationInDays(), o2.getDurationInDays());
        }
    }

    static class DurationDownComparator implements Comparator<Resort> {

        @Override
        public int compare(Resort o1, Resort o2) {
            return Integer.compare(o2.getDurationInDays(), o1.getDurationInDays());
        }
    }

    public void delete(long id) {
        resortRepository.deleteById(id);
    }

    public Collection<Resort> search(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                     Sorts sort) {
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
            if (resort.getCost() >= minCost & resort.getCost() <= maxCost & resort.getDurationInDays() >= minDuration
                    & resort.getDurationInDays() <= maxDuration & startDate.after(resort.getStartDate())) {
                res.add(resort);
            }
        }
        return res;
    }

    public Collection<Resort> search(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                     Sorts sort, HashMap<Integer, Country> acceptableCountries) {
        TreeSet<Resort> res = new TreeSet<>(search(minCost, maxCost, minDuration, maxDuration, startDate, sort));
        for (Resort resort : res
        ) {
            if (!acceptableCountries.containsValue(resort.getHotel().getCity().getCountry())) {
                res.remove(resort);
            }
        }
        return res;
    }
}
