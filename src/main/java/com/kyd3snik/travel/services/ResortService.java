package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.ResortRepository;
import com.kyd3snik.travel.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResortService {

    private final ResortRepository resortRepository;

    public ResortService(ResortRepository resortRepository) {
        this.resortRepository = resortRepository;
    }

    public void addResort(Resort resort) {
        resortRepository.save(resort);
    }

    public Resort getById(long id) {
        return resortRepository.findById(id).orElseThrow(() -> new IllegalStateException("Курорт не найден"));
    }

    public List<Resort> getAll() {
        return resortRepository.findAll();
    }

    public List<Resort> searchByTitle(String title) {
        return resortRepository.findByTitleContainingIgnoreCaseAndPurchasedIsFalseAndStartDateAfter(title, DateUtil.getToday());
    }

    public List<Resort> getAllAvailable() {
        return resortRepository.findAllByPurchasedFalseAndStartDateAfter(DateUtil.getToday());
    }

    public List<Resort> findAvailableByArrivalCity(City city) {
        return resortRepository.findByArrivalCityAndPurchasedIsFalseAndStartDateAfter(city, DateUtil.getToday());
    }

    public List<Resort> findByHotel(Hotel hotel) {
        return resortRepository.findByHotel(hotel);
    }

    public void update(Resort resort) {
        if (resortRepository.findById(resort.getId()).isPresent()) {
            resortRepository.save(resort);
        } else {
            throw new EntityNotFoundException("Отель не найден");
        }
    }

    public void delete(long id) {
        if (!getById(id).isPurchased()) {
            resortRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Невозможно удалить курорт, т.к. он уже куплен!");
        }
    }

    public void delete(List<Resort> resorts) {
        resorts.forEach(resort -> this.delete(resort.getId()));
    }

    public List<Resort> getResortsInCountry(Country country) {
        return resortRepository.findByArrivalCity_CountryAndPurchasedFalseAndStartDateAfter(country, DateUtil.getToday());
    }

    public List<Resort> search(SearchModel model) {
        return this.getAllAvailable().stream()
                .filter((resort) -> resort.getCost() >= model.getMinCost() && resort.getCost() <= model.getMaxCost())
                .filter((resort) -> resort.getDurationInDays() >= model.getMinDuration() && resort.getDurationInDays() <= model.getMaxDuration())
                .filter((resort) -> resort.getStartDate().after(model.getStartDate()))
                .filter((resort) -> model.getPersonCount() <= resort.getPersonCount())
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
