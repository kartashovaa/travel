package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.ResortRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
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
        return res.stream()
                .filter((resort) -> resort.getCost() >= minCost && resort.getCost() <= maxCost)
                .filter((resort) -> resort.getDurationInDays() >= minDuration && resort.getDurationInDays() <= maxDuration)
                .filter((resort) -> startDate.after(resort.getStartDate()))
                .collect(Collectors.toList());
    }

    //Список необходимых тегов
    public Collection<Resort> searchByTags(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                           SortType sort, List<Tag> necessaryTags) {
        TreeSet<Resort> res = new TreeSet<>(search(minCost, maxCost, minDuration, maxDuration, startDate, sort));
        return res.stream()
                .filter((resort) -> resort.getTags().containsAll(necessaryTags))
                .collect(Collectors.toList());
    }

    //Список допустимых стран
    public Collection<Resort> searchByCountry(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                              SortType sort, List<Country> acceptableCountries) {
        TreeSet<Resort> res = new TreeSet<>(search(minCost, maxCost, minDuration, maxDuration, startDate, sort));
        return res.stream()
                .filter((resort) -> acceptableCountries.contains(resort.getHotel().getCity().getCountry()))
                .collect(Collectors.toList());
    }

    //Список необходимых развлечений
    public Collection<Resort> searchByEntertainment(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                                    SortType sort, List<Entertainment> necessaryEntertainments) {
        TreeSet<Resort> res = new TreeSet<>(search(minCost, maxCost, minDuration, maxDuration, startDate, sort));
        return res.stream()
                .filter((resort) -> resort.getHotel().getCity().getEntertainments().containsAll(necessaryEntertainments))
                .collect(Collectors.toList());
    }

    //Минимальное количество звезд
    public Collection<Resort> searchByStars(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                            SortType sort, byte minStar) {
        TreeSet<Resort> res = new TreeSet<>(search(minCost, maxCost, minDuration, maxDuration, startDate, sort));
        return res.stream()
                .filter((resort) -> resort.getHotel().getStars() >= minStar)
                .collect(Collectors.toList());
    }

    //
    //Список необходимых удобств
    //

    //Список необходимых тегов + стран
    public Collection<Resort> searchByTagsAndCountries(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                                       SortType sort, List<Tag> necessaryTags, List<Country> acceptableCountries) {
        TreeSet<Resort> res = new TreeSet<>(searchByTags(minCost, maxCost, minDuration, maxDuration, startDate,
                sort, necessaryTags));
        return res.stream()
                .filter((resort) -> acceptableCountries.contains(resort.getHotel().getCity().getCountry()))
                .collect(Collectors.toList());
    }

    //Список необходимых тегов + развлечений
    public Collection<Resort> searchByTagsAndEntertainments(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                                            SortType sort, List<Tag> necessaryTags, List<Entertainment> necessaryEntertainments) {
        TreeSet<Resort> res = new TreeSet<>(searchByTags(minCost, maxCost, minDuration, maxDuration, startDate, sort, necessaryTags));
        return res.stream()
                .filter((resort) -> resort.getHotel().getCity().getEntertainments().containsAll(necessaryEntertainments))
                .collect(Collectors.toList());
    }

    //Список необходимых тегов + Минимальное количество звезд
    public Collection<Resort> searchByTagsAndStars(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                                   SortType sort, List<Tag> necessaryTags, byte minStar) {
        TreeSet<Resort> res = new TreeSet<>(searchByTags(minCost, maxCost, minDuration, maxDuration, startDate, sort, necessaryTags));
        return res.stream()
                .filter((resort) -> resort.getHotel().getStars() >= minStar)
                .collect(Collectors.toList());
    }

    //
    //Список необходимых тегов + Список необходимых удобств
    //

    //Список стран + городов
    public Collection<Resort> searchByCountriesAndCities(int minCost, int maxCost, int minDuration, int maxDuration, Date startDate,
                                                         SortType sort, List<Country> acceptableCountries, List<City> acceptableCities) {
        TreeSet<Resort> res = new TreeSet<>(searchByCountry(minCost, maxCost, minDuration, maxDuration, startDate, sort, acceptableCountries));
        return res.stream()
                .filter((resort) -> acceptableCities.contains(resort.getHotel().getCity()))
                .collect(Collectors.toList());
    }
}
