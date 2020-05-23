package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.ResortRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.Date;
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
        return resortRepository.findAll().stream()
                .filter((resort) -> resort.getCost() >= minCost && resort.getCost() <= maxCost)
                .filter((resort) -> resort.getDurationInDays() >= minDuration && resort.getDurationInDays() <= maxDuration)
                .filter((resort) -> resort.getStartDate().after(startDate))
                .filter((resort) -> resort.getTags().containsAll(necessaryTags))
                .filter((resort) -> acceptableCountries.contains(resort.getArrivalCity().getCountry()))
                .filter((resort) -> acceptableCities.contains(resort.getArrivalCity()))
                .filter((resort) -> resort.getHotel().getCity().getEntertainments().containsAll(necessaryEntertainments))
                .filter((resort) -> resort.getHotel().getStars() >= minStar)
                .filter((resort) -> getAllFacilities(resort.getHotel()).containsAll(necessaryFacilities))
                .sorted(getCoparator(sort))
                .collect(Collectors.toList());
    }

    private List<Facility> getAllFacilities(Hotel hotel) {
        return hotel.getRooms().stream().flatMap(room -> room.getFacilities().stream()).collect(Collectors.toList());
    }

    private Comparator<? super Resort> getCoparator(SortType sort) {
        switch (sort) {
            case COST_DOWN:
                return (o1, o2) -> Float.compare(o2.getCost(), o1.getCost());
            case DURATION_UP:
                return Comparator.comparingInt(Resort::getDurationInDays);
            case DURATION_DOWN:
                return (o1, o2) -> Integer.compare(o2.getDurationInDays(), o1.getDurationInDays());
            case COST_UP:
            default:
                return Comparator.comparingDouble(Resort::getCost);
        }
    }
}
