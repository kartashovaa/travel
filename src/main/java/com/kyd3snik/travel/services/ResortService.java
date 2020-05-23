package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.ResortRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
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

    public List<Resort> search(SearchModel searchModel) {
        //TODO: refactor this
        return getAll();
//        return search(searchModel.getMinCost(),
//                searchModel.getMaxCost(),
//                searchModel.getMinDuration(),
//                searchModel.getMaxDuration(),
//                searchModel.getStartDate(),
//                searchModel.getSortType(),
//                searchModel.getNecessaryTags(),
//                searchModel.getAcceptableCountries(),
//                searchModel.getAcceptableCities(),
//                searchModel.getNecessaryEntertainments(),
//                searchModel.getMinStar(),
//                searchModel.getNecessaryFacilities());
    }

    private List<Resort> search(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                SortType sort, List<Tag> necessaryTags, List<Country> acceptableCountries,
                                List<City> acceptableCities, List<Entertainment> necessaryEntertainments,
                                int minStar, List<Facility> necessaryFacilities) {
        TreeSet<Resort> res;
        switch (sort) {
            case COST_DOWN:
                res = new TreeSet<>((o1, o2) -> Float.compare(o2.getCost(), o1.getCost()));
                break;
            case DURATION_UP:
                res = new TreeSet<>(Comparator.comparingInt(Resort::getDurationInDays));
                break;
            case DURATION_DOWN:
                res = new TreeSet<>(Comparator.comparingDouble(Resort::getCost));
                break;
            case COST_UP:
            default:
                res = new TreeSet<>((o1, o2) -> Integer.compare(o2.getDurationInDays(), o1.getDurationInDays()));
        }
        res.addAll(resortRepository.findAll());
        return res.stream()
                .filter((resort) -> resort.getCost() >= minCost && resort.getCost() <= maxCost)
                .filter((resort) -> resort.getDurationInDays() >= minDuration && resort.getDurationInDays() <= maxDuration)
                .filter((resort) -> resort.getStartDate().after(startDate))
                .filter((resort) -> resort.getTags().containsAll(necessaryTags))
                .filter((resort) -> acceptableCountries.contains(resort.getArrivalCity().getCountry()))
                .filter((resort) -> acceptableCities.contains(resort.getArrivalCity()))
                .filter((resort) -> resort.getHotel().getCity().getEntertainments().containsAll(necessaryEntertainments))
                .filter((resort) -> resort.getHotel().getStars() >= minStar)
//                .filter((resort) -> resort.getHotelRoom().getFacilities().containsAll(necessaryFacilities))
                .collect(Collectors.toList());
    }
}
